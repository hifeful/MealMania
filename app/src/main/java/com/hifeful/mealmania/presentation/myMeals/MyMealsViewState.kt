package com.hifeful.mealmania.presentation.myMeals

import com.hifeful.mealmania.domain.model.Meal

data class MyMealsViewState(
    val recentMeals: List<Meal>?,
    val favouriteMeals: List<Meal>?
)