package com.hifeful.mealmania.presentation.mealsSearch

import com.hifeful.mealmania.domain.model.Meal

data class MealsSearchViewState(
    val foundMeals: List<Meal>?,
    val mealsLoadingError: Throwable?
)