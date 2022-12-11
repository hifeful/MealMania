package com.hifeful.mealmania.presentation.myMeals

import com.hifeful.mealmania.databinding.FragmentMyMealsBinding

fun FragmentMyMealsBinding.bind(
    viewState: MyMealsViewState,
    recentMealsAdapter: RecentMealsAdapter?,
    favouriteMealsAdapter: FavouriteMealsAdapter?
) {
    recentMealsAdapter?.submitList(viewState.recentMeals)
    recyclerViewFavouriteMeals.post {
        recyclerViewFavouriteMeals.smoothScrollToPosition(0)
    }
    favouriteMealsAdapter?.submitList(viewState.favouriteMeals)
}