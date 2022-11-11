package com.hifeful.mealmania.presentation.home

class HomeUiEventTransformer : (HomeUiEvent) -> HomeFeature.Wish {

    override fun invoke(event: HomeUiEvent): HomeFeature.Wish = when(event) {
        is HomeUiEvent.LoadRandomMeal -> HomeFeature.Wish.LoadRandomMeal
        is HomeUiEvent.LoadLatestMeals -> HomeFeature.Wish.LoadLatestMeals
    }
}