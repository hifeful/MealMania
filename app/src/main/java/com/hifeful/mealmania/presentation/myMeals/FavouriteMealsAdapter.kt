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

    // It's not a listener, it's just a lambda expression.
    // So, it would be better to have:
    //     val onClickMeal: (String) -> Unit = {}
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
                // Then, you could omit .invoke() and just use ()
                root.setOnClickListener { onMealClickListener?.invoke(meal.id) }
                imageViewFavouriteMeal.loadUrlCircleCrop(meal.thumbnail)
                textViewFavouriteMealName.text = meal.name
            }
        }
    }
}