package com.hifeful.mealmania.data.source.meals

import com.hifeful.mealmania.data.source.meals.dto.MealResponse
import com.hifeful.mealmania.data.source.meals.dto.MealsResponse
import com.hifeful.mealmania.domain.model.Ingredient
import com.hifeful.mealmania.domain.model.Meal

private const val INGREDIENT_THUMBNAIL_URL = "https://www.themealdb.com/images/ingredients/"

fun MealsResponse.toMeals(): List<Meal> =
    this.meals.map { it.toMeal() }

fun MealResponse.toMeal(): Meal {
    return Meal(
        id = id,
        name = name,
        category = category,
        area = area,
        instructions = instructions,
        thumbnail = thumbnail,
        drinkAlternate = drinkAlternate,
        youtubeSource = youtubeSource,
        ingredients = mapIngredients()
    )
}

private fun MealResponse.mapIngredients(): List<Ingredient> {
    val responseIngredients = listOfNotNull(
        ingredient1,
        ingredient2,
        ingredient3,
        ingredient4,
        ingredient5,
        ingredient6,
        ingredient7,
        ingredient8,
        ingredient9,
        ingredient10,
        ingredient11,
        ingredient12,
        ingredient13,
        ingredient14,
        ingredient15,
        ingredient16,
        ingredient17,
        ingredient18,
        ingredient19,
        ingredient20
    ).filter { it.isNotBlank() }

    val responseMeasures = listOfNotNull(
        measure1,
        measure2,
        measure3,
        measure4,
        measure5,
        measure6,
        measure7,
        measure8,
        measure9,
        measure10,
        measure11,
        measure12,
        measure13,
        measure14,
        measure15,
        measure16,
        measure17,
        measure18,
        measure19,
        measure20
    ).filter { it.isNotBlank() }

    return responseIngredients.zip(responseMeasures).map {
        val imageUrl = "$INGREDIENT_THUMBNAIL_URL${it.first}.png"
        Ingredient(it.first, it.second, imageUrl)
    }
}