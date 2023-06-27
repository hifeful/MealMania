package com.hifeful.mealmania.presentation.mealsSearch

import android.view.View
import androidx.core.view.isVisible
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

        if (mealsLoadingError != null) {
            linearLayoutNoMealsFound.isVisible = false
            linearLayoutMealLoadingError.isVisible = true
        }
    }

    mealsSearchView.setOnBackPressListener { onSearchBackPressListener.invoke() }
}