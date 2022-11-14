package com.hifeful.mealmania.data.db.recentMeals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.Single

@Dao
interface RecentMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentMeal(recentMeal: RecentMeal): Single<Long>
}