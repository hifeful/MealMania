package com.hifeful.mealmania.domain.model

data class Meal(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnail: String,
    val drinkAlternate: String?,
    val youtubeSource: String?,
    val ingredients: List<Ingredient>
)
