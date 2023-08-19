package com.hifeful.mealmania.presentation.details

import android.content.Context
import android.content.res.ColorStateList
import android.support.test.runner.AndroidJUnit4
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import com.hifeful.mealmania.domain.model.Ingredient
import com.hifeful.mealmania.domain.model.Meal
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MealDetailsViewStateTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    @Test
    fun `Given favorite meal, When getFavouriteIcon is called, Then holo red dark should be returned`() {
        // Given
        val expectedColorStateList = ColorStateList.valueOf(
            ContextCompat.getColor(context, android.R.color.holo_red_dark)
        )
        val viewState = MealDetailsViewState(
            meal = provideMeal(),
            isFavourite = true
        )

        // When
        val actual = viewState.getFavouriteIcon(context)

        // Then
        assert(actual == expectedColorStateList)
    }

    @Test
    fun `Given not favorite meal, When getFavouriteIcon is called, Then while color should be returned`() {
        // Given
        val expectedColorStateList = ColorStateList.valueOf(
            ContextCompat.getColor(context, android.R.color.white)
        )
        val viewState = MealDetailsViewState(
            meal = provideMeal(),
        )

        // When
        val actual = viewState.getFavouriteIcon(context)

        // Then
        assert(actual == expectedColorStateList)
    }

    private fun provideMeal(
        id: String = "",
        name: String = "",
        category: String = "",
        area: String = "",
        instructions: String = "",
        thumbnail: String = "",
        drinkAlternate: String? = null,
        youtubeSource: String? = null,
        ingredients: List<Ingredient> = emptyList()
    ): Meal =
        Meal(
            id = id,
            name = name,
            category = category,
            area = area,
            instructions = instructions,
            thumbnail = thumbnail,
            drinkAlternate = drinkAlternate,
            youtubeSource = youtubeSource,
            ingredients = ingredients
        )
}