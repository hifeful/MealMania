package com.hifeful.mealmania.presentation.home

import com.hifeful.mealmania.domain.model.Meal

data class HomeViewState(
    val randomMeals: List<Meal>?,
    val randomMealError: Throwable?,
    val latestMeals: List<Meal>
)