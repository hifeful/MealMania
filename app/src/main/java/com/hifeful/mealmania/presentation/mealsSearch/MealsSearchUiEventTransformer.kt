package com.hifeful.mealmania.presentation.mealsSearch


class MealsSearchUiEventTransformer : (MealsSearchUiEvent) -> MealsSearchFeature.Wish {

    override fun invoke(event: MealsSearchUiEvent): MealsSearchFeature.Wish =
        when (event) {
            is MealsSearchUiEvent.SearchMeals -> MealsSearchFeature.Wish.SearchMeals(event.query)
        }
}