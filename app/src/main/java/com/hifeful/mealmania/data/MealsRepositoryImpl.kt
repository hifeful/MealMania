package com.hifeful.mealmania.data

import android.util.Log
import com.hifeful.mealmania.data.db.meals.MealsLocalSource
import com.hifeful.mealmania.data.db.recentMeals.RecentMeal
import com.hifeful.mealmania.data.db.recentMeals.RecentMealsLocalSource
import com.hifeful.mealmania.data.source.meals.MealsRemoteSource
import com.hifeful.mealmania.data.source.meals.toMeal
import com.hifeful.mealmania.data.source.meals.toMeals
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.domain.repository.MealsRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val mealsRemoteSource: MealsRemoteSource,
    private val mealsLocalSource: MealsLocalSource,
    private val recentMealsLocalSource: RecentMealsLocalSource
) : MealsRepository {

    override fun getMealsByName(name: String): Observable<List<Meal>> {
        return mealsRemoteSource.getMealsByName(name)
            .map { it.toMeals() }
    }

    override fun getMealById(id: String): Observable<Meal> {
        return mealsRemoteSource.getMealById(id)
            .flatMapIterable { it.meals }
            .map { it.toMeal() }
    }

    override fun getRandomMeals(): Observable<List<Meal>> {
        return Observable.range(0, 5)
            .flatMap { 
                mealsRemoteSource.getRandomMeal()
                    .flatMapIterable { it.meals }
                    .map { it.toMeal() }
            }
            .toList()
            .toObservable()
    }

    override fun getLatestMeals(): Observable<List<Meal>> {
        return mealsRemoteSource.getLatestMeals()
            .map { it.meals.map { mealResponse -> mealResponse.toMeal() } }
    }

    override fun addRecentMeal(meal: Meal): Single<Long> {
        val updatedMeal = meal.copy(favouriteTimestamp = System.currentTimeMillis())

        return mealsLocalSource.addMeal(updatedMeal).flatMap {
            val recentMeal = RecentMeal(mealId = meal.id, addedTime = System.currentTimeMillis())
            recentMealsLocalSource.addRecentMeal(recentMeal)
        }
    }

    override fun getRecentMeals(): Observable<List<Meal>> {
        return recentMealsLocalSource.getRecentMeals()
            .flatMap {
                val observables = it.map { recentMeal ->
                    mealsLocalSource.getMealById(recentMeal.mealId).toObservable()
                }

                Observable.merge(observables).toList().toObservable()
            }
            .doOnNext { Log.d("MealsRepositoryImpl", it?.map { meal -> meal.id }.toString()) }
    }

    override fun updateFavouriteMeal(id: String, isFavourite: Boolean): Single<Int> =
        mealsLocalSource.updateFavouriteMeal(id, isFavourite, System.currentTimeMillis())

    override fun isMealFavourite(id: String): Single<Boolean> =
        mealsLocalSource.isMealFavourite(id)

    override fun getFavouriteMeals(): Observable<List<Meal>> =
        mealsLocalSource.getFavouriteMeals()
}