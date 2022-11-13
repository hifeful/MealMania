package com.hifeful.mealmania.domain.repository

import com.hifeful.mealmania.domain.model.Meal
import io.reactivex.Observable

interface MealsRepository {

    fun getMealsByName(name: String): Observable<List<Meal>>
    fun getMealById(id: String): Observable<Meal>
    fun getRandomMeal(): Observable<Meal>
    fun getLatestMeals(): Observable<List<Meal>>
}