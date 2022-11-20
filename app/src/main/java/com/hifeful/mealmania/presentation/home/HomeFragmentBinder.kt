package com.hifeful.mealmania.presentation.home

import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.hifeful.mealmania.databinding.FragmentHomeBinding
import com.hifeful.mealmania.presentation.home.randomMeals.RandomMealFragment
import com.hifeful.mealmania.presentation.home.randomMeals.RandomMealsAdapter

fun FragmentHomeBinding.bind(
    viewState: HomeViewState?,
    latestMealsAdapter: LatestMealsAdapter?,
    fragmentActivity: FragmentActivity
) {
    viewState?.randomMeals?.let {
        val randomMealsAdapter = RandomMealsAdapter(fragmentActivity)
        viewPagerRandomMeals.adapter = randomMealsAdapter
        randomMealsAdapter.randomMealFragments = it.map { meal ->
            RandomMealFragment.getInstance(meal)
        }.toMutableList()

        TabLayoutMediator(tabLayoutRandomMeals, viewPagerRandomMeals) { tab, _ ->
            viewPagerRandomMeals.setCurrentItem(tab.position, true) }
            .attach()
    }
    latestMealsAdapter?.submitList(viewState?.latestMeals)
}