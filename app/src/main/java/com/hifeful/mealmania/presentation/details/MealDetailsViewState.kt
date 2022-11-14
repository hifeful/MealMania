package com.hifeful.mealmania.presentation.details

import com.hifeful.mealmania.domain.model.Meal

data class MealDetailsViewState(
    val meal: Meal?,
    val isAddedToRecent: Boolean = false
)