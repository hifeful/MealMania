package com.hifeful.mealmania.di

import com.hifeful.mealmania.domain.repository.MealsRepository
import com.hifeful.mealmania.presentation.details.MealDetailsFeature
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class MealDetailsModule {

    @Provides
    fun provideMealDetailsFeature(
        mealsRepository: MealsRepository
    ): MealDetailsFeature =
        MealDetailsFeature(
            initialState = MealDetailsFeature.State(),
            actor = MealDetailsFeature.ActorImpl(mealsRepository),
            reducer = MealDetailsFeature.ReducerImpl()
        )
}