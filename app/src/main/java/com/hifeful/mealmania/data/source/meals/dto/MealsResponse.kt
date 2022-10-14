package com.hifeful.mealmania.data.source.meals.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealsResponse(
    val meals: List<MealResponse>
)
