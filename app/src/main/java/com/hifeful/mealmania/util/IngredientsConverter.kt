package com.hifeful.mealmania.util

import androidx.room.TypeConverter
import com.hifeful.mealmania.domain.model.Ingredient
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class IngredientsConverter {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val parametrizedType =
        Types.newParameterizedType(List::class.java, Ingredient::class.java)
    private val jsonAdapter: JsonAdapter<List<Ingredient>> = moshi.adapter(parametrizedType)

    @TypeConverter
    fun ingredientsToJson(ingredients: List<Ingredient>): String? {
        return jsonAdapter.toJson(ingredients)
    }

    @TypeConverter
    fun jsonToIngredients(jsonString: String?): List<Ingredient>? {
        return jsonString?.let { jsonAdapter.fromJson(jsonString) }
    }
}