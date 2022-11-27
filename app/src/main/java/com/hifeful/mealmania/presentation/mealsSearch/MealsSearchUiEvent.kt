package com.hifeful.mealmania.presentation.mealsSearch

sealed class MealsSearchUiEvent {
    data class SearchMeals(val query: String) : MealsSearchUiEvent()
}