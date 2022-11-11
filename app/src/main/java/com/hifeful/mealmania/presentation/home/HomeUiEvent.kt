package com.hifeful.mealmania.presentation.home

sealed class HomeUiEvent {
    object LoadRandomMeal : HomeUiEvent()
    object LoadLatestMeals : HomeUiEvent()
}