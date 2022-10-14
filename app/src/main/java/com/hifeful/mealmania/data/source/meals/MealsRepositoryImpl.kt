package com.hifeful.mealmania.data.source.meals

import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.domain.repository.MealsRepository
import io.reactivex.Observable

class MealsRepositoryImpl(
    private val mealsRemoteSource: MealsRemoteSource
) : MealsRepository {

    override fun getMealsByName(name: String): Observable<List<Meal>> {
        return mealsRemoteSource.getMealsByName(name)
            .map { it.toMeals() }
    }

    override fun getRandomMeal(): Observable<Meal> {
        return mealsRemoteSource.getRandomMeal()
            .flatMapIterable { it.meals }
            .map { it.toMeal() }
    }
}