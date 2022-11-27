package com.hifeful.mealmania.di

import com.hifeful.mealmania.domain.repository.MealsRepository
import com.hifeful.mealmania.presentation.mealsSearch.MealsSearchFeature
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class MealsSearchModule {

    @Provides
    fun provideMealsSearchFeature(
        mealsRepository: MealsRepository
    ): MealsSearchFeature =
        MealsSearchFeature(
            initialState = MealsSearchFeature.State(),
            actor = MealsSearchFeature.ActorImpl(mealsRepository),
            reducer = MealsSearchFeature.ReducerImpl()
        )
}