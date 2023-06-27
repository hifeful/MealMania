package com.hifeful.mealmania.presentation.mealsSearch

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.domain.repository.MealsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MealsSearchFeature @Inject constructor(
    initialState: State,
    actor: Actor<State, Wish, Effect>,
    reducer: Reducer<State, Effect>
) : ActorReducerFeature<MealsSearchFeature.Wish,
        MealsSearchFeature.Effect,
        MealsSearchFeature.State,
        Nothing>(initialState = initialState, actor = actor, reducer = reducer) {

    data class State(
        val foundMeals: List<Meal>? = null,
        val mealsLoadingError: Throwable? = null
    )

    sealed class Wish {
        data class SearchMeals(val query: String) : Wish()
    }

    sealed class Effect {
        data class MealsFound(val meals: List<Meal>) : Effect()
        data class MealsLoadingError(val throwable: Throwable) : Effect()
    }

    class ActorImpl(
        private val mealsRepository: MealsRepository
    ) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, action: Wish): Observable<out Effect> =
            when (action) {
                is Wish.SearchMeals -> mealsRepository.getMealsByName(action.query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.MealsFound(it) as Effect }
                    .onErrorReturn { Effect.MealsLoadingError(it) as Effect }
            }
    }

    class ReducerImpl : Reducer<State, Effect> {

        override fun invoke(state: State, effect: Effect): State =
            when (effect) {
                is Effect.MealsFound -> state.copy(
                    foundMeals = effect.meals,
                    mealsLoadingError = null
                )
                is Effect.MealsLoadingError -> state.copy(mealsLoadingError = effect.throwable)
            }
    }
}