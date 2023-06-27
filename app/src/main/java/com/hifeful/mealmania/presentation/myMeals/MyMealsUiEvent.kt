package com.hifeful.mealmania.presentation.myMeals

sealed interface MyMealsUiEvent {

    object LoadRecentMeals : MyMealsUiEvent
    object LoadFavouriteMeals : MyMealsUiEvent
}