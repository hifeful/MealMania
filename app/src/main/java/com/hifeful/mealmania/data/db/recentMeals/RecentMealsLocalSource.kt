package com.hifeful.mealmania.data.db.recentMeals

import javax.inject.Inject

class RecentMealsLocalSource @Inject constructor(
    private val recentMealDao: RecentMealDao
) {

    fun addRecentMeal(recentMeal: RecentMeal) =
        recentMealDao.insertRecentMeal(recentMeal)
}