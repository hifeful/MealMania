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

//    @Provides
//    fun provideHomeFeatureState(): HomeFeature.State =
//        HomeFeature.State()

//    @Provides
//    fun provideHomeFeatureActor(
//        mealsRepository: MealsRepository
//    ): Actor<HomeFeature.State, HomeFeature.Wish, HomeFeature.Effect> =
//        HomeFeature.ActorImpl(mealsRepository)

//    @Provides
//    fun provideHomeFeatureReducer(): Reducer<HomeFeature.State, HomeFeature.Effect> =
//        HomeFeature.ReducerImpl()

    @Provides
    fun provideHomeFeature(
        mealsRepository: MealsRepository
//        initialState: HomeFeature.State,
//        actor: Actor<HomeFeature.State, HomeFeature.Wish, HomeFeature.Effect>,
//        reducer: Reducer<HomeFeature.State, HomeFeature.Effect>
    ): HomeFeature =
        HomeFeature(HomeFeature.State(), HomeFeature.ActorImpl(mealsRepository), HomeFeature.ReducerImpl())

//    @Provides
//    fun provideHomeFragmentBindings(
//        fragment: HomeFragment,
//        homeFeature: HomeFeature
//    ): HomeFragmentBindings =
//        HomeFragmentBindings(view = fragment, feature = homeFeature)
}