package com.hifeful.mealmania.presentation.home.randomMeals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.hifeful.mealmania.R
import com.hifeful.mealmania.databinding.FragmentRandomMealBinding
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.presentation.details.MealDetailsFragment
import com.hifeful.mealmania.presentation.util.loadUrl

class RandomMealFragment : Fragment() {

    private var _binding: FragmentRandomMealBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomMealBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Meal>(MEAL_KEY)?.let {
            setUpViews(it)
        }
    }

    private fun setUpViews(meal: Meal) {
        with(binding) {
            root.setOnClickListener { attachMealDetailsFragment(meal.id) }
            textViewRandomMealName.text = meal.name
            imageViewRandomMeal.loadUrl(meal.thumbnail)
        }
    }

    private fun attachMealDetailsFragment(id: String) {
        parentFragmentManager.commit {
            add(R.id.fragment_container_view, MealDetailsFragment.getInstance(id))
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    companion object {

        private const val MEAL_KEY = "MEAL_KEY"

        fun getInstance(meal: Meal): RandomMealFragment {
            return RandomMealFragment().apply {
                arguments = bundleOf(MEAL_KEY to meal)
            }
        }
    }
}