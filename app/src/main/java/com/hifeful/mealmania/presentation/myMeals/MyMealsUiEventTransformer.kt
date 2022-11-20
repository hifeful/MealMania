package com.hifeful.mealmania.presentation.myMeals

class MyMealsUiEventTransformer : (MyMealsUiEvent) -> MyMealsFeature.Wish {

    override fun invoke(event: MyMealsUiEvent): MyMealsFeature.Wish = when (event) {
        is MyMealsUiEvent.LoadRecentMeals -> MyMealsFeature.Wish.LoadRecentMeals
        is MyMealsUiEvent.LoadFavouriteMeals -> MyMealsFeature.Wish.LoadFavouriteMeals
    }
}