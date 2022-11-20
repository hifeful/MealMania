package com.hifeful.mealmania.data.db.meals

import com.hifeful.mealmania.domain.model.Meal
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MealsLocalSource @Inject constructor(
    private val mealDao: MealDao
) {

    fun addMeal(meal: Meal): Single<Long> =
        mealDao.insertMeal(meal)

    fun getMealById(id: String): Single<Meal> =
        mealDao.getMealById(id)

    fun updateFavouriteMeal(id: String, isFavourite: Boolean, timestamp: Long): Single<Int> =
        mealDao.updateFavouriteMeal(id, isFavourite, timestamp)

    fun isMealFavourite(id: String): Single<Boolean> =
        mealDao.isMealFavourite(id)

    fun getFavouriteMeals(): Observable<List<Meal>> =
        mealDao.getFavouriteMeals()
}