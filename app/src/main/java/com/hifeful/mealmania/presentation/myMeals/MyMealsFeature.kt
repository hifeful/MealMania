package com.hifeful.mealmania.presentation.myMeals

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.domain.repository.MealsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyMealsFeature @Inject constructor(
    initialState: State,
    actor: Actor<State, Wish, Effect>,
    reducer: Reducer<State, Effect>
) : ActorReducerFeature<MyMealsFeature.Wish,
        MyMealsFeature.Effect,
        MyMealsFeature.State,
        Nothing>(initialState = initialState, actor = actor, reducer = reducer) {

    data class State(
        val recentMeals: List<Meal>? = null,
        val favouriteMeals: List<Meal>? = null
    )

    sealed class Wish {
        object LoadRecentMeals : Wish()
        object LoadFavouriteMeals : Wish()
    }

    sealed class Effect {
        data class LoadedRecentMeals(val meals: List<Meal>) : Effect()
        data class LoadedFavouriteMeals(val meals: List<Meal>) : Effect()
    }

    class ActorImpl(
        private val mealsRepository: MealsRepository
    ) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, action: Wish): Observable<out Effect> =
            when (action) {
                is Wish.LoadRecentMeals -> mealsRepository.getRecentMeals()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.LoadedRecentMeals(it) }
                is Wish.LoadFavouriteMeals -> mealsRepository.getFavouriteMeals()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.LoadedFavouriteMeals(it) }
            }
    }

    class ReducerImpl : Reducer<State, Effect> {

        override fun invoke(state: State, effect: Effect): State =
            when (effect) {
                is Effect.LoadedRecentMeals -> state.copy(recentMeals = effect.meals)
                is Effect.LoadedFavouriteMeals -> state.copy(favouriteMeals = effect.meals)
            }

    }
}