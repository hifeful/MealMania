package com.hifeful.mealmania.presentation.home

class HomeViewStateTransformer : (HomeFeature.State) -> HomeViewState {

    override fun invoke(state: HomeFeature.State): HomeViewState {

        return HomeViewState(
            randomMeal = state.randomMeal,
            randomMealError = state.randomMealLoadingError,
            latestMeals = state.latestMeals
        )
    }
}