package com.hifeful.mealmania.presentation.home

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.domain.repository.MealsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeFeature @Inject constructor(
    initialState: State,
    actor: Actor<State, Wish, Effect>,
    reducer: Reducer<State, Effect>
) : ActorReducerFeature<HomeFeature.Wish, HomeFeature.Effect, HomeFeature.State, Nothing>(
    initialState = initialState,
    actor = actor,
    reducer = reducer
) {

    data class State(
        val randomMeals: List<Meal>? = null,
        val randomMealLoadingError: Throwable? = null,
        val latestMeals: List<Meal> = emptyList()
    )

    sealed class Wish {
        object LoadRandomMeals : Wish()
        object LoadLatestMeals : Wish()
    }

    sealed class Effect {
        data class LoadedRandomMeals(val meals: List<Meal>) : Effect()
        data class ErrorLoadingRandomMeal(val throwable: Throwable) : Effect()
        data class LoadedLatestMeals(val latestMeals: List<Meal>) : Effect()
    }

    class ActorImpl(
        private val mealsRepository: MealsRepository
    ) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, action: Wish): Observable<out Effect> =
            when (action) {
                is Wish.LoadRandomMeals -> mealsRepository.getRandomMeals()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.LoadedRandomMeals(it) as Effect }
                    .onErrorReturn { Effect.ErrorLoadingRandomMeal(it) }
                is Wish.LoadLatestMeals -> mealsRepository.getLatestMeals()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.LoadedLatestMeals(it) as Effect }
            }
    }

    class ReducerImpl : Reducer<State, Effect> {

        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is Effect.LoadedRandomMeals -> state.copy(
                randomMeals = effect.meals,
                randomMealLoadingError = null
            )
            is Effect.ErrorLoadingRandomMeal -> state.copy(randomMealLoadingError = effect.throwable)
            is Effect.LoadedLatestMeals -> state.copy(latestMeals = effect.latestMeals)
        }
    }
}