package com.hifeful.mealmania.data.source.meals

import com.hifeful.mealmania.data.source.meals.dto.MealResponse
import com.hifeful.mealmania.data.source.meals.dto.MealsResponse
import com.hifeful.mealmania.domain.model.Meal

fun MealsResponse.toMeals(): List<Meal> =
    this.meals.map { it.toMeal() }

fun MealResponse.toMeal() = Meal(id, name, category, area, instructions, thumbnail)