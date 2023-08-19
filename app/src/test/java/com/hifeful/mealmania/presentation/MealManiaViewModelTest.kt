package com.hifeful.mealmania.presentation

import android.net.Uri
import android.support.test.runner.AndroidJUnit4
import com.hifeful.mealmania.presentation.util.DeeplinkResult
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MealManiaViewModelTest {
    private val viewModel = MealManiaViewModel()

    @Test
    fun `Given mymeals page deeplink, When handleDeeplink is called, Then ShowMyMealsPage DeeplinkResult should be returned`() {
        // Given
        val uri = Uri.parse("mealmania://mymeals")
        
        // When
        val actual = viewModel.handleDeeplink(uri)

        // Then
        assert(actual is DeeplinkResult.ShowMyMealsPage)
    }

    @Test
    fun `Given meals search deeplink, When handleDeeplink is called, Then ApplyMealsSearch DeeplinkResult should be returned`() {
        // Given
        val uri = Uri.parse("mealmania://search?q=soup")

        // When
        val actual = viewModel.handleDeeplink(uri)

        // Then
        assert(actual == DeeplinkResult.ApplyMealsSearch("soup"))
    }

    @Test
    fun `Given meals details page deeplink, When handleDeeplink is called, Then ShowMealsPage DeeplinkResult should be returned`() {
        // Given
        val uri = Uri.parse("mealmania://meal?id=123")

        // When
        val actual = viewModel.handleDeeplink(uri)

        // Then
        assert(actual == DeeplinkResult.ShowMealsPage("123"))
    }

    @Test
    fun `Given not supported deeplink, When handleDeeplink is called, Then null should be returned`() {
        // Given
        val uri = Uri.parse("mealmania://receipt?id=123&name=potato")

        // When
        val actual = viewModel.handleDeeplink(uri)

        // Then
        assert(actual == null)
    }

    @Test
    fun `Given unknown deeplink, When handleDeeplink is called, Then null should be returned`() {
        // Given
        val uri = Uri.parse("unknown")

        // When
        val actual = viewModel.handleDeeplink(uri)

        // Then
        assert(actual == null)
    }

    @Test
    fun `Given mymeals page firebase dynamic link, When handleFirebaseDynamicLink is called, Then ShowMyMealsPage DeeplinkResult should be returned`() {
        // Given
        val uri = Uri.parse("https://mealmania.page.link/mymeals")

        // When
        val actual = viewModel.handleFirebaseDynamicLink(uri)

        // Then
        assert(actual is DeeplinkResult.ShowMyMealsPage)
    }

    @Test
    fun `Given meals search firebase dynamic link, When handleFirebaseDynamicLink is called, Then ApplyMealsSearch DeeplinkResult should be returned`() {
        // Given
        val uri = Uri.parse("https://mealmania.link/search?q=soup")

        // When
        val actual = viewModel.handleFirebaseDynamicLink(uri)

        // Then
        assert(actual == DeeplinkResult.ApplyMealsSearch("soup"))
    }

    @Test
    fun `Given meals details page firebase dynamic link, When handleFirebaseDynamicLink is called, Then ShowMealsPage DeeplinkResult should be returned`() {
        // Given
        val uri = Uri.parse("https://mealmania.link/meal?id=123")

        // When
        val actual = viewModel.handleFirebaseDynamicLink(uri)

        // Then
        assert(actual == DeeplinkResult.ShowMealsPage("123"))
    }

    @Test
    fun `Given not supported firebase dynamic link, When handleFirebaseDynamicLink is called, Then null should be returned`() {
        // Given
        val uri = Uri.parse("https://mealmania.link/receipt?id=123&name=potato")

        // When
        val actual = viewModel.handleFirebaseDynamicLink(uri)

        // Then
        assert(actual == null)
    }
}