package com.hifeful.mealmania.data.source.meals

import com.hifeful.mealmania.data.source.meals.dto.MealsResponse
import io.reactivex.Observable
import javax.inject.Inject

class MealsRemoteSource @Inject constructor(
    private val mealsApiService: MealsApiService
) {

    fun getMealsByName(name: String): Observable<MealsResponse> =
        mealsApiService.getMealsByName(name)

    fun getRandomMeal(): Observable<MealsResponse> =
        mealsApiService.getRandomMeal()
}
