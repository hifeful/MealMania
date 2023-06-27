package com.hifeful.mealmania.presentation.mealsSearch

class MealsSearchViewStateTransformer : (MealsSearchFeature.State) -> MealsSearchViewState {

    override fun invoke(state: MealsSearchFeature.State): MealsSearchViewState {

        return MealsSearchViewState(
            foundMeals = state.foundMeals,
            mealsLoadingError = state.mealsLoadingError
        )
    }
}