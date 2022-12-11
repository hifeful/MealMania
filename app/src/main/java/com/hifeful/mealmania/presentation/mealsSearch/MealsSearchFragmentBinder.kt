package com.hifeful.mealmania.presentation.mealsSearch

import android.view.View
import com.hifeful.mealmania.databinding.FragmentMealsSearchBinding

fun FragmentMealsSearchBinding.bind(
    viewState: MealsSearchViewState,
    foundMealsAdapter: FoundMealsAdapter?,
    onSearchBackPressListener: () -> Unit
) {
    with(viewState) {
        linearLayoutNoMealsFound.visibility = if (foundMeals == null) {
            View.VISIBLE
        } else {
            View.GONE
        }

        foundMealsAdapter?.submitList(foundMeals)
    }

    mealsSearchView.setOnBackPressListener { onSearchBackPressListener.invoke() }
}