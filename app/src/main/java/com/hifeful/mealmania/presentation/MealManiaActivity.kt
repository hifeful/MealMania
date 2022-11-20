package com.hifeful.mealmania.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.hifeful.mealmania.R
import com.hifeful.mealmania.databinding.ActivityMealManiaBinding
import com.hifeful.mealmania.presentation.home.HomeFragment
import com.hifeful.mealmania.presentation.myMeals.MyMealsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealManiaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealManiaBinding

    private var activeFragment: Fragment? = null
    private val homeFragment: Fragment = HomeFragment()
    private val myMealsFragment: Fragment = MyMealsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMealManiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        addBottomNavigationFragments()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeItem -> {
                    showFragment(homeFragment)
                    activeFragment = homeFragment
                    return@setOnItemSelectedListener true
                }
                R.id.myMealsPage -> {
                    showFragment(myMealsFragment)
                    activeFragment = myMealsFragment
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
        binding.bottomNavigation.setOnItemReselectedListener {  }
    }

    private fun addBottomNavigationFragments() {
        supportFragmentManager.commit {
            add(R.id.fragment_container_view, myMealsFragment).hide(myMealsFragment)
            add(R.id.fragment_container_view, homeFragment)
            activeFragment = homeFragment
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            activeFragment?.let { hide(it) }
            show(fragment)
        }
    }
}