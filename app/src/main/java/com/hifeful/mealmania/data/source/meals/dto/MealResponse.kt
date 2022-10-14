package com.hifeful.mealmania.data.source.meals.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@Entity(tableName = "meal")
@JsonClass(generateAdapter = true)
data class MealResponse(
//    @PrimaryKey
    @Json(name = "idMeal")
    val id: String,
    @Json(name = "strMeal")
    val name: String,
    @Json(name = "strCategory")
    val category: String,
    @Json(name = "strArea")
    val area: String,
    @Json(name = "strInstructions")
    val instructions: String,
    @Json(name = "strMealThumb")
    val thumbnail: String
)
