package com.hifeful.mealmania.presentation.myMeals

import androidx.recyclerview.widget.RecyclerView
import com.hifeful.mealmania.databinding.FragmentMyMealsBinding

fun FragmentMyMealsBinding.bind(
    viewState: MyMealsState,
    recentMealsAdapter: RecentMealsAdapter?,
    favouriteMealsAdapter: FavouriteMealsAdapter?
) {
    recentMealsAdapter?.submitList(viewState.recentMeals)

    favouriteMealsAdapter?.apply {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                recyclerViewFavouriteMeals.smoothScrollToPosition(0)

            }
        })
        submitList(viewState.favouriteMeals)
    }
}