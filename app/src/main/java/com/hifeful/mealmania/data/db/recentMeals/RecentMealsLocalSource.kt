package com.hifeful.mealmania.data.db.recentMeals

import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RecentMealsLocalSource @Inject constructor(
    private val recentMealDao: RecentMealDao
) {

    fun addRecentMeal(recentMeal: RecentMeal): Single<Long> =
        recentMealDao.insertRecentMeal(recentMeal)

    fun getRecentMeals(): Observable<List<RecentMeal>> =
        recentMealDao.getRecentMeals()
}