package com.hifeful.mealmania.presentation.details

import android.content.Context
import android.content.res.ColorStateList
import android.support.test.runner.AndroidJUnit4
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import io.mockk.mockk
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
            meal = mockk(),
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
            meal = mockk(),
        )

        // When
        val actual = viewState.getFavouriteIcon(context)

        // Then
        assert(actual == expectedColorStateList)
    }
}