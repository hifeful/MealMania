package com.hifeful.mealmania.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.hifeful.mealmania.R
import com.hifeful.mealmania.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealManiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_mania)

        attachHomeFragment()
    }

    private fun attachHomeFragment() {
        supportFragmentManager.commit {
            add(R.id.fragment_container_view, HomeFragment())
        }
    }
}