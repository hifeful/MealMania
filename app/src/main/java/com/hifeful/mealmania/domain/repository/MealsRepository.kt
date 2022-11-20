package com.hifeful.mealmania.domain.repository

import com.hifeful.mealmania.domain.model.Meal
import io.reactivex.Observable
import io.reactivex.Single

interface MealsRepository {

    fun getMealsByName(name: String): Observable<List<Meal>>
    fun getMealById(id: String): Observable<Meal>
    fun getRandomMeals(): Observable<List<Meal>>
    fun getLatestMeals(): Observable<List<Meal>>
    fun addRecentMeal(meal: Meal): Single<Long>
    fun getRecentMeals(): Observable<List<Meal>>
    fun updateFavouriteMeal(id: String, isFavourite: Boolean): Single<Int>
    fun isMealFavourite(id: String): Single<Boolean>
    fun getFavouriteMeals(): Observable<List<Meal>>
}