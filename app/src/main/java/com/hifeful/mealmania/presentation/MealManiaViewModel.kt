package com.hifeful.mealmania.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.hifeful.mealmania.presentation.util.DeeplinkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealManiaViewModel @Inject constructor(): ViewModel() {

    fun handleDeeplink(appLinkData: Uri): DeeplinkResult? =
        handlePage(appLinkData.host, appLinkData)

    fun handleFirebaseDynamicLink(appLinkData: Uri): DeeplinkResult? =
        handlePage(appLinkData.path?.replace(PATH_DELIMITER, EMPTY_CHARACTER), appLinkData)

    private fun handlePage(pageString: String?, appLinkData: Uri): DeeplinkResult? =
        when (pageString) {
            MY_MEALS_PAGE -> DeeplinkResult.ShowMyMealsPage
            MEALS_PAGE -> handleMealsHost(appLinkData)
            MEALS_SEARCH_PAGE -> handleMealsSearchHost(appLinkData)
            else -> null
        }

    private fun handleMealsHost(appLinkData: Uri): DeeplinkResult? {
        val queries = splitQueries(appLinkData) ?: return null
        queries.forEach {
            val values = it.split(PAIR_DELIMITER)
            if (values.size == 2 && values[0] == ID_QUERY) {
                return DeeplinkResult.ShowMealsPage(values[1])
            }
        }

        return null
    }

    private fun handleMealsSearchHost(appLinkData: Uri): DeeplinkResult? {
        val queries = splitQueries(appLinkData) ?: return null

        queries.forEach {
            val values = it.split(PAIR_DELIMITER)
            if (values.size == 2 && values[0] == QUERY) {
                return DeeplinkResult.ApplyMealsSearch(values[1])
            }
        }

        return null
    }

    private fun splitQueries(appLinkData: Uri): List<String>? {
        val fullQuery = appLinkData.query ?: return null

        return fullQuery.split(QUERIES_DELIMITER)
    }

    companion object {
        private const val QUERIES_DELIMITER = "&"
        private const val PAIR_DELIMITER = "="
        private const val PATH_DELIMITER = "/"
        private const val EMPTY_CHARACTER = ""

        private const val MY_MEALS_PAGE = "mymeals"
        private const val MEALS_PAGE = "meal"
        private const val MEALS_SEARCH_PAGE = "search"

        private const val ID_QUERY = "id"
        private const val QUERY = "q"
    }
}