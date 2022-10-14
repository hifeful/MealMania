package com.hifeful.mealmania.presentation.home

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.domain.repository.MealsRepository
import io.reactivex.Observable

class HomeFeature(
    initialState: State,
    actor: Actor<State, Wish, Effect>,
    reducer: Reducer<State, Effect>
) : ActorReducerFeature<HomeFeature.Wish, HomeFeature.Effect, HomeFeature.State, Nothing>(
    initialState = initialState,
    actor = actor,
    reducer = reducer
) {

    data class State(
        val randomMeal: Meal? = null,
        val randomMealLoadingError: Throwable? = null
    )

    sealed class Wish {
        object LoadRandomMeal : Wish()
    }

    sealed class Effect {
        data class LoadedRandomMeal(val meal: Meal) : Effect()
        data class ErrorLoadingRandomMeal(val throwable: Throwable) : Effect()
    }

    class ActorImpl(
        private val mealsRepository: MealsRepository
    ) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, action: Wish): Observable<out Effect> =
            when (action) {
                is Wish.LoadRandomMeal -> mealsRepository.getRandomMeal()
                    .map { Effect.LoadedRandomMeal(it) as Effect }
                    .onErrorReturn { Effect.ErrorLoadingRandomMeal(it) }
            }
    }

    class ReducerImpl : Reducer<State, Effect> {

        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is Effect.LoadedRandomMeal -> state.copy(
                randomMeal = effect.meal,
                randomMealLoadingError = null
            )
            is Effect.ErrorLoadingRandomMeal -> state.copy(randomMealLoadingError = effect.throwable)
        }
    }
}