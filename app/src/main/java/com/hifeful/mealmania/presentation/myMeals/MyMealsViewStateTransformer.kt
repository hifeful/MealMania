package com.hifeful.mealmania.presentation.myMeals

class MyMealsViewStateTransformer : (MyMealsFeature.State) -> MyMealsViewState {

    override fun invoke(state: MyMealsFeature.State): MyMealsViewState {

        return MyMealsViewState(
            recentMeals = state.recentMeals,
            favouriteMeals = state.favouriteMeals
        )
    }
}