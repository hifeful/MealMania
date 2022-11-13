package com.hifeful.mealmania.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hifeful.mealmania.databinding.ItemLatestMealBinding
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.presentation.util.MealsDiffCallback
import com.hifeful.mealmania.presentation.util.loadUrl

class LatestMealsAdapter :
    ListAdapter<Meal, LatestMealsAdapter.LatestMealsViewHolder>(MealsDiffCallback()) {

    var onMealClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestMealsViewHolder =
        LatestMealsViewHolder(
            ItemLatestMealBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: LatestMealsViewHolder, position: Int) =
        holder.bind(currentList[position])

    inner class LatestMealsViewHolder(
        private val binding: ItemLatestMealBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal) {
            with(binding) {
                root.setOnClickListener { onMealClickListener?.invoke(meal.id) }
                imageViewLatestMeal.loadUrl(meal.thumbnail)
                textViewLatestMealName.text = meal.name
                val latestMealInfo = listOf(meal.category, meal.area).joinToString(separator = ", ")
                textViewLatestMealInfo.text = latestMealInfo
            }
        }
    }
}