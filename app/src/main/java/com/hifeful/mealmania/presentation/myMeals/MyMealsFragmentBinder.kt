package com.hifeful.mealmania.presentation.myMeals

import androidx.recyclerview.widget.RecyclerView
import com.hifeful.mealmania.databinding.FragmentMyMealsBinding

fun FragmentMyMealsBinding.bind(
    viewState: MyMealsViewState,
    recentMealsAdapter: RecentMealsAdapter?,
    favouriteMealsAdapter: FavouriteMealsAdapter?
) {
    recentMealsAdapter?.submitList(viewState.recentMeals)

    favouriteMealsAdapter?.apply {
        val adapterDataObserver = object : RecyclerView.AdapterDataObserver() {

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                recyclerViewFavouriteMeals.smoothScrollToPosition(0)
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                recyclerViewFavouriteMeals.smoothScrollToPosition(0)

            }
        }

        registerAdapterDataObserver(adapterDataObserver)
        submitList(viewState.favouriteMeals)
    }
}