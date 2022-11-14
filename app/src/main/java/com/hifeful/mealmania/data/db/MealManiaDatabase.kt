package com.hifeful.mealmania.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hifeful.mealmania.data.db.meals.MealDao
import com.hifeful.mealmania.data.db.recentMeals.RecentMeal
import com.hifeful.mealmania.data.db.recentMeals.RecentMealDao
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.util.IngredientsConverter

@Database(entities = [Meal::class, RecentMeal::class], version = 1)
@TypeConverters(IngredientsConverter::class)
abstract class MealManiaDatabase : RoomDatabase() {

    abstract fun getMealDao(): MealDao
    abstract fun getRecentMealDao(): RecentMealDao

    companion object {

        const val DATABASE_NAME = "meal_mania.db"
    }
}