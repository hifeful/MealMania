package com.hifeful.mealmania.presentation.details

class MealDetailsViewStateTransformer : (MealDetailsFeature.State) -> MealDetailsViewState {

    override fun invoke(state: MealDetailsFeature.State): MealDetailsViewState {

        return MealDetailsViewState(
            meal = state.meal,
            mealLoadingError = state.mealLoadingError,
            isAddedToRecent = state.isAddedToRecent,
            isMealLoaded = state.isMealLoaded,
            isFavourite = state.isFavourite
        )
    }
}