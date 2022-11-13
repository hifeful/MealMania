package com.hifeful.mealmania.presentation.details

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.domain.repository.MealsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MealDetailsFeature @Inject constructor(
    initialState: State,
    actor: Actor<State, Wish, Effect>,
    reducer: Reducer<State, Effect>
) : ActorReducerFeature<MealDetailsFeature.Wish,
        MealDetailsFeature.Effect,
        MealDetailsFeature.State,
        Nothing>(initialState = initialState, actor = actor, reducer = reducer) {

    data class State(
        val meal: Meal? = null
    )

    sealed class Wish {
        data class LoadMealDetails(val id: String): Wish()
    }

    sealed class Effect {
        data class LoadedMeal(val meal: Meal) : Effect()
    }

    class ActorImpl(
        private val mealsRepository: MealsRepository
    ) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, action: Wish): Observable<out Effect> =
            when (action) {
                is Wish.LoadMealDetails -> mealsRepository.getMealById(action.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.LoadedMeal(it) }
            }
    }

    class ReducerImpl : Reducer<State, Effect> {

        override fun invoke(state: State, effect: Effect): State =
            when (effect) {
                is Effect.LoadedMeal -> state.copy(
                    meal = effect.meal
                )
            }
    }
}