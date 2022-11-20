package com.hifeful.mealmania.presentation.myMeals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hifeful.mealmania.databinding.ItemFavouriteMealBinding
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.presentation.util.MealsDiffCallback
import com.hifeful.mealmania.presentation.util.loadUrlCircleCrop

class FavouriteMealsAdapter : ListAdapter<Meal, FavouriteMealsAdapter.FavouriteMealsViewHolder>(
    MealsDiffCallback()
) {

    var onMealClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteMealsViewHolder =
        FavouriteMealsViewHolder(
            ItemFavouriteMealBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: FavouriteMealsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class FavouriteMealsViewHolder(
        private val binding: ItemFavouriteMealBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal) {
            with(binding) {
                root.setOnClickListener { onMealClickListener?.invoke(meal.id) }
                imageViewFavouriteMeal.loadUrlCircleCrop(meal.thumbnail)
                textViewFavouriteMealName.text = meal.name
            }
        }
    }
}