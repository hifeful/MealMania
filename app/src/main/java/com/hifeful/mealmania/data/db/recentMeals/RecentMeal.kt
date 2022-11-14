package com.hifeful.mealmania.data.db.recentMeals

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_meal")
data class RecentMeal(
    @PrimaryKey
    @ColumnInfo(name = "meal_id")
    val mealId: String,
    @ColumnInfo(name = "added_time")
    val addedTime: Long
)