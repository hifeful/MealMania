package com.hifeful.mealmania.presentation.details

class MealDetailsUiEventTransformer : (MealDetailsUiEvent) -> MealDetailsFeature.Wish {

    override fun invoke(event: MealDetailsUiEvent): MealDetailsFeature.Wish = when (event) {
        is MealDetailsUiEvent.LoadMealDetails -> MealDetailsFeature.Wish.LoadMealDetails(event.id)
        is MealDetailsUiEvent.AddIntoRecentMeals ->
            MealDetailsFeature.Wish.AddIntoRecentMeals(event.meal)
        is MealDetailsUiEvent.ClickFavourite ->
            MealDetailsFeature.Wish.ClickFavourite(id = event.id)
        is MealDetailsUiEvent.IsFavourite ->
            MealDetailsFeature.Wish.IsFavourite(id = event.id)
    }
}