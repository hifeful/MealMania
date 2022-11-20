package com.hifeful.mealmania.data.db.recentMeals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface RecentMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentMeal(recentMeal: RecentMeal): Single<Long>

    @Query("SELECT * FROM recent_meal ORDER BY added_time DESC")
    fun getRecentMeals(): Observable<List<RecentMeal>>
}