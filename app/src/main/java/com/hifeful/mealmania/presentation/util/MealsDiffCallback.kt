package com.hifeful.mealmania.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.hifeful.mealmania.domain.model.Meal

class MealsDiffCallback : DiffUtil.ItemCallback<Meal>() {

    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem == newItem
}