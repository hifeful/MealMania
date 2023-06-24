package com.hifeful.mealmania.presentation.myMeals

// Better to keep it as the sealed interface
sealed class MyMealsUiEvent {

    object LoadRecentMeals : MyMealsUiEvent()
    object LoadFavouriteMeals : MyMealsUiEvent()
}