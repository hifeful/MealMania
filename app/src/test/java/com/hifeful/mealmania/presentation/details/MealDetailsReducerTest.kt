package com.hifeful.mealmania.presentation.details

import org.junit.Test


class MealDetailsReducerTest {

    private val reducer = MealDetailsFeature.ReducerImpl()

    @Test
    fun `Given not favorite meal, When invoke is called, Then the state should be favorite`() {
        // Given
        val previousState = MealDetailsFeature.State(isFavourite = false)
        val effect = MealDetailsFeature.Effect.FavouriteClicked(1)

        // When
        val actual = reducer.invoke(previousState, effect)

        // Then
        assert(actual.isFavourite == true)
    }

    @Test
    fun `Given favorite meal, When invoke is called, Then the state should be not favorite`() {
        // Given
        val previousState = MealDetailsFeature.State(isFavourite = true)
        val effect = MealDetailsFeature.Effect.FavouriteClicked(1)

        // When
        val actual = reducer.invoke(previousState, effect)

        // Then
        assert(actual.isFavourite == false)
    }
}