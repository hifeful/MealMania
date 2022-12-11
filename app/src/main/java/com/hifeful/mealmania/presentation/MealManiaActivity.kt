package com.hifeful.mealmania.presentation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.hifeful.mealmania.R
import com.hifeful.mealmania.databinding.ActivityMealManiaBinding
import com.hifeful.mealmania.presentation.details.MealDetailsFragment
import com.hifeful.mealmania.presentation.home.HomeFragment
import com.hifeful.mealmania.presentation.mealsSearch.MealsSearchFragment
import com.hifeful.mealmania.presentation.myMeals.MyMealsFragment
import com.hifeful.mealmania.presentation.util.DeeplinkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealManiaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealManiaBinding

    lateinit var mealManiaViewModel: MealManiaViewModel

    private var activeFragment: Fragment? = null
    private val homeFragment: Fragment = HomeFragment()
    private val myMealsFragment: Fragment = MyMealsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mealManiaViewModel = ViewModelProvider(this)[MealManiaViewModel::class.java]

        binding = ActivityMealManiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBottomNavigation()

        val appLinkAction: String? = intent?.action
        val appLinkData: Uri? = intent?.data
        handleDeeplink(appLinkAction, appLinkData)
        handleFirebaseDynamicLink()
    }

    private fun handleDeeplink(appLinkAction: String?, appLinkData: Uri?) {
        if (Intent.ACTION_VIEW == appLinkAction && appLinkData != null) {
            Log.d("MealManiaActivity", appLinkData.toString())
            handleDeeplinkResult(mealManiaViewModel.handleDeeplink(appLinkData))
        }
    }

    private fun handleFirebaseDynamicLink() {
        FirebaseDynamicLinks
            .getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener {
                Log.d("MealManiaActivity", it?.link?.path.toString())
                it?.link?.let { uri ->
                    handleDeeplinkResult(mealManiaViewModel.handleFirebaseDynamicLink(uri))
                }
            }
    }

    private fun handleDeeplinkResult(deeplinkResult: DeeplinkResult?) {
        when (deeplinkResult) {
            is DeeplinkResult.ShowMyMealsPage -> {
                binding.bottomNavigation.selectedItemId = R.id.myMealsPage
            }
            is DeeplinkResult.ShowMealsPage -> {
                attachMealDetailsFragment(deeplinkResult.mealId)
            }
            is DeeplinkResult.ApplyMealsSearch -> {
                attachMealsSearchFragment(deeplinkResult.searchQuery)
            }
            else -> {}
        }
    }

    private fun setUpBottomNavigation() {
        addBottomNavigationFragments()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeItem -> {
                    toggleFragment(homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.myMealsPage -> {
                    toggleFragment(myMealsFragment)
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

    private fun toggleFragment(fragment: Fragment) {
        showFragment(fragment)
        activeFragment = fragment
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            activeFragment?.let { hide(it) }
            show(fragment)
        }
    }

    private fun attachMealDetailsFragment(id: String) {
        supportFragmentManager.commit {
            add(R.id.fragment_container_view, MealDetailsFragment.getInstance(id))
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun attachMealsSearchFragment(query: String) {
        supportFragmentManager.commit {
            add(R.id.fragment_container_view, MealsSearchFragment.getInstance(query))
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}