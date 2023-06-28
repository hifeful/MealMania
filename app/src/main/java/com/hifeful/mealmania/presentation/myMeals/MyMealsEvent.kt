package com.hifeful.mealmania.presentation.myMeals

sealed interface MyMealsEvent {

    object LoadRecentMeals : MyMealsEvent
    object LoadFavouriteMeals : MyMealsEvent
}