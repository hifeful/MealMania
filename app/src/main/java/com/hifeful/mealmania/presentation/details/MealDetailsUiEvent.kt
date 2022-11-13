package com.hifeful.mealmania.presentation.details

sealed class MealDetailsUiEvent {
    data class LoadMealDetails(val id: String): MealDetailsUiEvent()
}