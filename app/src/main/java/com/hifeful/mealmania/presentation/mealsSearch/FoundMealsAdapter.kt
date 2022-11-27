package com.hifeful.mealmania.presentation.mealsSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hifeful.mealmania.databinding.ItemFoundMealBinding
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.presentation.util.MealsDiffCallback
import com.hifeful.mealmania.presentation.util.loadUrl

class FoundMealsAdapter :
    ListAdapter<Meal, FoundMealsAdapter.FoundMealViewHolder>(MealsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoundMealViewHolder =
        FoundMealViewHolder(
            ItemFoundMealBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: FoundMealViewHolder, position: Int) =
        holder.bind(currentList[position])

    inner class FoundMealViewHolder(
        private val binding: ItemFoundMealBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal) {
            with(binding) {
                imageViewMeal.loadUrl(meal.thumbnail)
                textViewMealName.text = meal.name
                textViewMealDescription.text = meal.instructions
                textViewMealCategory.text = meal.category
            }
        }
    }
}