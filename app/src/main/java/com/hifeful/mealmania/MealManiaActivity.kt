package com.hifeful.mealmania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.hifeful.mealmania.home.HomeFragment

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