package com.hifeful.mealmania.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.hifeful.mealmania.R
import com.hifeful.mealmania.databinding.ActivityMealManiaBinding
import com.hifeful.mealmania.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealManiaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealManiaBinding

    private var activeFragment: Fragment? = null
    private val homeFragment: Fragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMealManiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBottomNavigation()
        attachHomeFragment()
    }

    private fun attachHomeFragment() {
        supportFragmentManager.commit {
            add(R.id.fragment_container_view, homeFragment)
        }
    }

    private fun setUpBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeItem -> {
                    replaceFragment(homeFragment)
                    activeFragment = homeFragment
                    return@setOnItemSelectedListener true
                }
                R.id.myMealsPage -> {
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
        binding.bottomNavigation.setOnItemReselectedListener {  }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, fragment)
        }
    }
}