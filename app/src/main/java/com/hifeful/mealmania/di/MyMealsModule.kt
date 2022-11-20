package com.hifeful.mealmania.di

import com.hifeful.mealmania.domain.repository.MealsRepository
import com.hifeful.mealmania.presentation.myMeals.MyMealsFeature
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class MyMealsModule {

    @Provides
    fun provideMyMealsFeature(
        mealsRepository: MealsRepository
    ): MyMealsFeature =
        MyMealsFeature(
            initialState = MyMealsFeature.State(),
            actor = MyMealsFeature.ActorImpl(mealsRepository),
            reducer = MyMealsFeature.ReducerImpl()
        )
}