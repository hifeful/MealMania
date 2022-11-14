package com.hifeful.mealmania.data.db.meals

import com.hifeful.mealmania.domain.model.Meal
import io.reactivex.Single
import javax.inject.Inject

class MealsLocalSource @Inject constructor(
    private val mealDao: MealDao
) {

    fun addMeal(meal: Meal): Single<Long> =
        mealDao.insertMeal(meal)
}