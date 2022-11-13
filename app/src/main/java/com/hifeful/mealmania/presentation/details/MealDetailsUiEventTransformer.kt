package com.hifeful.mealmania.presentation.details

class MealDetailsUiEventTransformer : (MealDetailsUiEvent) -> MealDetailsFeature.Wish {

    override fun invoke(event: MealDetailsUiEvent): MealDetailsFeature.Wish = when (event) {
        is MealDetailsUiEvent.LoadMealDetails -> MealDetailsFeature.Wish.LoadMealDetails(event.id)
    }
}