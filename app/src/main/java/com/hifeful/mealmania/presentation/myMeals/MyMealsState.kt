package com.hifeful.mealmania.presentation.myMeals

import com.hifeful.mealmania.domain.model.Meal

data class MyMealsState(
    val recentMeals: List<Meal> = emptyList(),
    val favouriteMeals: List<Meal> = emptyList()
)