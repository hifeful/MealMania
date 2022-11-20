package com.hifeful.mealmania.presentation.myMeals

import com.hifeful.mealmania.databinding.FragmentMyMealsBinding

fun FragmentMyMealsBinding.bind(
    viewState: MyMealsViewState,
    recentMealsAdapter: RecentMealsAdapter?,
    favouriteMealsAdapter: FavouriteMealsAdapter?
) {
    recentMealsAdapter?.submitList(viewState.recentMeals)
    favouriteMealsAdapter?.submitList(viewState.favouriteMeals)
}