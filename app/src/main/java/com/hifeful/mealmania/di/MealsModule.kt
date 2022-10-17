package com.hifeful.mealmania.di

import com.hifeful.mealmania.data.source.meals.MealsApiService
import com.hifeful.mealmania.data.source.meals.MealsRepositoryImpl
import com.hifeful.mealmania.domain.repository.MealsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
interface MealsModule {

    @Binds
    fun bindMealsRepository(mealsRepositoryImpl: MealsRepositoryImpl): MealsRepository

    companion object {

        @Provides
        fun provideMealsApiService(retrofit: Retrofit): MealsApiService =
            retrofit.create(MealsApiService::class.java)
    }
}