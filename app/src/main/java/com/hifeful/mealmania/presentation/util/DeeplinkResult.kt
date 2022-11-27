package com.hifeful.mealmania.presentation.util

sealed class DeeplinkResult {
    object ShowMyMealsPage : DeeplinkResult()
    data class ShowMealsPage(val mealId: String) : DeeplinkResult()
    data class ApplyMealsSearch(val searchQuery: String) : DeeplinkResult()
}