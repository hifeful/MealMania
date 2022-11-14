package com.hifeful.mealmania.data.db.meals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hifeful.mealmania.domain.model.Meal
import io.reactivex.Single

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(meal: Meal): Single<Long>
}