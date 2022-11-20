package com.hifeful.mealmania.presentation.myMeals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hifeful.mealmania.databinding.ItemRecentlyViewedMealBinding
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.presentation.util.MealsDiffCallback
import com.hifeful.mealmania.presentation.util.loadUrl

class RecentMealsAdapter :
    ListAdapter<Meal, RecentMealsAdapter.RecentMealsViewHolder>(MealsDiffCallback()) {

    var onMealClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMealsViewHolder =
        RecentMealsViewHolder(
            ItemRecentlyViewedMealBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RecentMealsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class RecentMealsViewHolder(
        private val binding: ItemRecentlyViewedMealBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal) {
            with(binding) {
                root.setOnClickListener { onMealClickListener?.invoke(meal.id) }
                imageViewMeal.loadUrl(meal.thumbnail)
                textViewMeal.text = meal.name
            }
        }
    }
}