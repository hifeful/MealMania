package com.hifeful.mealmania.presentation.details

import com.hifeful.mealmania.databinding.FragmentMealDetailsBinding
import com.hifeful.mealmania.presentation.util.loadUrl

fun FragmentMealDetailsBinding.bind(
    viewState: MealDetailsViewState?
) {
    viewState?.meal?.let {
        imageViewMeal.loadUrl(it.thumbnail)
        mealName.text = it.name
        mealCategory.text = it.category
        textViewInstructions.text = it.instructions
        if (viewState.isFavourite != null) fabFavourite.show()
        fabFavourite.imageTintList = viewState.getFavouriteIcon(root.context)

        val ingredientsAdapter = IngredientsAdapter()
        recyclerViewIngredients.adapter = ingredientsAdapter
        ingredientsAdapter.ingredients = it.ingredients
    }
}