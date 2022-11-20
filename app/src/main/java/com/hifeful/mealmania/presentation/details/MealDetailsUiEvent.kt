package com.hifeful.mealmania.presentation.details

import com.hifeful.mealmania.domain.model.Meal

sealed class MealDetailsUiEvent {
    data class LoadMealDetails(val id: String): MealDetailsUiEvent()
    data class AddIntoRecentMeals(val meal: Meal): MealDetailsUiEvent()
    data class ClickFavourite(val id: String) : MealDetailsUiEvent()
    data class IsFavourite(val id: String) : MealDetailsUiEvent()
}