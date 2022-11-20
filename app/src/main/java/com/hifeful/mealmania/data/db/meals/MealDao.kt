package com.hifeful.mealmania.data.db.meals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hifeful.mealmania.domain.model.Meal
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMeal(meal: Meal): Single<Long>

    @Query("SELECT * FROM meal WHERE id = :id")
    fun getMealById(id: String): Single<Meal>

    @Query("UPDATE meal SET is_favourite = :isFavourite, favourite_timestamp = :timestamp WHERE id = :id")
    fun updateFavouriteMeal(id: String, isFavourite: Boolean, timestamp: Long): Single<Int>

    @Query("SELECT EXISTS(SELECT * FROM meal WHERE id = :id AND is_favourite = 1)")
    fun isMealFavourite(id: String): Single<Boolean>

    @Query("SELECT * FROM meal WHERE is_favourite = 1 ORDER BY favourite_timestamp DESC")
    fun getFavouriteMeals(): Observable<List<Meal>>
}