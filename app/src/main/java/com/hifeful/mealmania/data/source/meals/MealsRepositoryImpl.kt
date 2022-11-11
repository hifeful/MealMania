package com.hifeful.mealmania.data.source.meals

import android.util.Log
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.domain.repository.MealsRepository
import io.reactivex.Observable
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val mealsRemoteSource: MealsRemoteSource
) : MealsRepository {

    override fun getMealsByName(name: String): Observable<List<Meal>> {
        return mealsRemoteSource.getMealsByName(name)
            .map { it.toMeals() }
    }

    override fun getRandomMeal(): Observable<Meal> {
        Log.d("MealsRepositoryImpl", "loading random meal")
        return mealsRemoteSource.getRandomMeal()
            .flatMapIterable { it.meals }
            .map { it.toMeal() }
    }

    override fun getLatestMeals(): Observable<List<Meal>> {
        return mealsRemoteSource.getLatestMeals()
            .map { it.meals.map { mealResponse -> mealResponse.toMeal() } }
    }
}