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
        val meal: Meal? = null,
        val mealLoadingError: Throwable? = null,
        val isMealLoaded: Boolean = false,
        val isAddedToRecent: Boolean = false,
        val isFavourite: Boolean? = null
    )

    sealed class Wish {
        data class LoadMealDetails(val id: String): Wish()
        data class AddIntoRecentMeals(val meal: Meal): Wish()
        data class IsFavourite(val id: String) : Wish()
        data class ClickFavourite(val id: String): Wish()
    }

    sealed class Effect {
        data class LoadedMeal(val meal: Meal) : Effect()
        data class MealLoadingError(val throwable: Throwable) : Effect()
        object MealAddedIntoRecent : Effect()
        data class IsFavourite(val isFavourite: Boolean) : Effect()
        data class FavouriteClicked(val updatedRowsCount: Int) : Effect()
    }

    class ActorImpl(
        private val mealsRepository: MealsRepository
    ) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, action: Wish): Observable<out Effect> =
            when (action) {
                is Wish.LoadMealDetails -> mealsRepository.getMealById(action.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.LoadedMeal(it) as Effect }
                    .onErrorReturn { Effect.MealLoadingError(it) as Effect }

                is Wish.AddIntoRecentMeals -> mealsRepository.addRecentMeal(action.meal)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.MealAddedIntoRecent }
//                    .onError { Log.e("MealDetailsFeature", it.toString()) }
                    .toObservable()
                is Wish.ClickFavourite -> mealsRepository.updateFavouriteMeal(action.id, state.isFavourite?.not() ?: true)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.FavouriteClicked(it) }
                    .toObservable()
                is Wish.IsFavourite -> mealsRepository.isMealFavourite(action.id)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.IsFavourite(it) }
                    .toObservable()
            }
    }

    class ReducerImpl : Reducer<State, Effect> {

        override fun invoke(state: State, effect: Effect): State =
            when (effect) {
                is Effect.LoadedMeal -> state.copy(
                    meal = effect.meal,
                    isMealLoaded = true,
                )
                is Effect.MealLoadingError -> state.copy(
                    mealLoadingError = effect.throwable,
                    isMealLoaded = false
                )
                is Effect.MealAddedIntoRecent -> state.copy(isAddedToRecent = true)
                is Effect.IsFavourite -> state.copy(isFavourite = effect.isFavourite)
                is Effect.FavouriteClicked ->  {
                    val isFavourite = if (effect.updatedRowsCount != 0) {
                        state.isFavourite?.not()
                    } else {
                        state.isFavourite
                    }

                    state.copy(isFavourite = isFavourite)
                }
            }
    }
}