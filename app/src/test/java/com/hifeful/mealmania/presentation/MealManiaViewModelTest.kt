package com.hifeful.mealmania.presentation

import android.net.Uri
import android.support.test.runner.AndroidJUnit4
import com.hifeful.mealmania.presentation.util.DeeplinkResult
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MealManiaViewModelTest {
    private val viewModel = MealManiaViewModel()

    @Test
    fun `with mymeals page deeplink - on handleDeeplink - it should return ShowMyMealsPage DeeplinkResult`() {
        val uri = Uri.parse("mealmania://mymeals")
        
        val actual = viewModel.handleDeeplink(uri)

        Assertions.assertThat(actual).isInstanceOf(DeeplinkResult.ShowMyMealsPage::class.java)
    }

    @Test
    fun `with meals search deeplink - on handleDeeplink - it should return ApplyMealsSearch DeeplinkResult`() {
        val uri = Uri.parse("mealmania://search?q=soup")

        val actual = viewModel.handleDeeplink(uri)

        Assertions.assertThat(actual)
            .isEqualTo(DeeplinkResult.ApplyMealsSearch("soup"))
    }

    @Test
    fun `with meals details page deeplink - on handleDeeplink - it should return ShowMealsPage DeeplinkResult`() {
        val uri = Uri.parse("mealmania://meal?id=123")

        val actual = viewModel.handleDeeplink(uri)

        Assertions.assertThat(actual)
            .isEqualTo(DeeplinkResult.ShowMealsPage("123"))
    }

    @Test
    fun `with unsupported deeplink - on handleDeeplink - it should return no deeplink`() {
        val uri = Uri.parse("mealmania://receipt?id=123&name=potato")

        val actual = viewModel.handleDeeplink(uri)

        Assertions.assertThat(actual).isNull()
    }

    @Test
    fun `with unknown deeplink - on handleDeeplink - it should return null`() {
        val uri = Uri.parse("unknown")

        val actual = viewModel.handleDeeplink(uri)

        Assertions.assertThat(actual).isNull()
    }

    @Test
    fun `with mymeals page firebase dynamic link - on handleFirebaseDynamicLink - it should return ShowMyMealsPage DeeplinkResult`() {
        val uri = Uri.parse("https://mealmania.page.link/mymeals")

        val actual = viewModel.handleFirebaseDynamicLink(uri)

        Assertions.assertThat(actual).isInstanceOf(DeeplinkResult.ShowMyMealsPage::class.java)
    }

    @Test
    fun `with meals search firebase dynamic link - on handleFirebaseDynamicLink - it should return ApplyMealsSearch DeeplinkResult`() {
        val uri = Uri.parse("https://mealmania.link/search?q=soup")

        val actual = viewModel.handleFirebaseDynamicLink(uri)

        Assertions.assertThat(actual)
            .isEqualTo(DeeplinkResult.ApplyMealsSearch("soup"))
    }

    @Test
    fun `with meals details page firebase dynamic link - on handleFirebaseDynamicLink - it should return ShowMealsPage DeeplinkResult`() {
        val uri = Uri.parse("https://mealmania.link/meal?id=123")

        val actual = viewModel.handleFirebaseDynamicLink(uri)

        Assertions.assertThat(actual)
            .isEqualTo(DeeplinkResult.ShowMealsPage("123"))
    }

    @Test
    fun `with unsupported firebase dynamic link - on handleFirebaseDynamicLink - it should return null`() {
        val uri = Uri.parse("https://mealmania.link/receipt?id=123&name=potato")

        val actual = viewModel.handleFirebaseDynamicLink(uri)

        Assertions.assertThat(actual).isNull()
    }
}