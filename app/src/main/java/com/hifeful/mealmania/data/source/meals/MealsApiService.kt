package com.hifeful.mealmania.data.source.meals

import com.hifeful.mealmania.data.source.meals.dto.MealsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService {

    @GET("search.php")
    fun getMealsByName(
        @Query("s") name: String
    ): Observable<MealsResponse>

    @GET("lookup.php")
    fun getMealById(
        @Query("i") id: String
    ): Observable<MealsResponse>

    @GET("random.php")
    fun getRandomMeal(): Observable<MealsResponse>

    @GET("latest.php")
    fun getLatestMeals(): Observable<MealsResponse>
}