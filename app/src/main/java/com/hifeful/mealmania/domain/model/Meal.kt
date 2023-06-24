package com.hifeful.mealmania.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 *  1️⃣ It should be inside the data layer as it's a database entity(i.e., MealEntity)
 *  2️⃣ In the domain layer you are going to have a model (i.e., Meal) which is mapped
 *     in the data layer based on received entity
 *  3️⃣ Domain has only models and repository contracts
 *     In some cases, use-cases as well
 *
 *  🎁 When we have multi-modular architecture:
 *     > the domain layer shouldn't have any dependencies, even to another domain
 *     > the data layer should have only the dedicated domain dependency, and no another data layers
 *     > of course, there might be exception, but usually we should avoid it
 */
@Parcelize
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
    val ingredients: List<Ingredient>,
    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean = false,
    @ColumnInfo(name = "favourite_timestamp")
    val favouriteTimestamp: Long? = null
) : Parcelable
