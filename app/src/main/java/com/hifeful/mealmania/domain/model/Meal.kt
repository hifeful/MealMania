package com.hifeful.mealmania.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class Meal(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "area")
    val area: String,
    @ColumnInfo(name = "instructions")
    val instructions: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "drink_alternate")
    val drinkAlternate: String?,
    @ColumnInfo(name = "youtube_source")
    val youtubeSource: String?,
    @ColumnInfo(name = "ingredients")
    val ingredients: List<Ingredient>
)
