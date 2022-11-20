package com.hifeful.mealmania.presentation.myMeals

sealed class MyMealsUiEvent {

    object LoadRecentMeals : MyMealsUiEvent()
    object LoadFavouriteMeals : MyMealsUiEvent()
}