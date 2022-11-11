package com.hifeful.mealmania.presentation.home

import com.hifeful.mealmania.domain.model.Meal

data class HomeViewState(
    val randomMeal: Meal?,
    val randomMealError: Throwable?,
    val latestMeals: List<Meal>
)