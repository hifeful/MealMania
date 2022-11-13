package com.hifeful.mealmania.di

import com.hifeful.mealmania.domain.repository.MealsRepository
import com.hifeful.mealmania.presentation.home.HomeFeature
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class HomeModule {

    @Provides
    fun provideHomeFeature(
        mealsRepository: MealsRepository
    ): HomeFeature =
        HomeFeature(
            initialState = HomeFeature.State(),
            actor = HomeFeature.ActorImpl(mealsRepository),
            reducer = HomeFeature.ReducerImpl()
        )
}