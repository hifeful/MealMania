package com.hifeful.mealmania.presentation.home

import com.hifeful.mealmania.databinding.FragmentHomeBinding
import com.hifeful.mealmania.presentation.util.loadUrl

fun FragmentHomeBinding.bind(viewState: HomeViewState?) {
    viewState?.randomMeal?.let {
        imageViewRandomMeal.loadUrl(it.thumbnail)
        textViewRandomMealName.text = it.name
    }
}