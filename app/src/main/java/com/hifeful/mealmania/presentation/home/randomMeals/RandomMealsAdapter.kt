package com.hifeful.mealmania.presentation.home.randomMeals

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RandomMealsAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    var randomMealFragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = randomMealFragments.size

    override fun createFragment(position: Int): Fragment = randomMealFragments[position]
}