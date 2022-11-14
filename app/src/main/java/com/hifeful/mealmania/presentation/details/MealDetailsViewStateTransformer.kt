package com.hifeful.mealmania.presentation.details

class MealDetailsViewStateTransformer : (MealDetailsFeature.State) -> MealDetailsViewState {

    override fun invoke(state: MealDetailsFeature.State): MealDetailsViewState {

        return MealDetailsViewState(
            meal = state.meal,
            isAddedToRecent = state.isAddedToRecent
        )
    }
}