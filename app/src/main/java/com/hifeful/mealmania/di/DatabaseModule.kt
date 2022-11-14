package com.hifeful.mealmania.di

import android.content.Context
import androidx.room.Room
import com.hifeful.mealmania.data.db.MealManiaDatabase
import com.hifeful.mealmania.data.db.meals.MealDao
import com.hifeful.mealmania.data.db.recentMeals.RecentMealDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMealManiaDatabase(@ApplicationContext appContext: Context): MealManiaDatabase {
        return Room.databaseBuilder(
            appContext,
            MealManiaDatabase::class.java,
            MealManiaDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMealDao(mealManiaDatabase: MealManiaDatabase): MealDao {
        return mealManiaDatabase.getMealDao()
    }

    @Provides
    fun provideRecentMealDao(mealManiaDatabase: MealManiaDatabase): RecentMealDao {
        return mealManiaDatabase.getRecentMealDao()
    }
}