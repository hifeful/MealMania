package com.hifeful.mealmania.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hifeful.mealmania.databinding.ItemIngredientBinding
import com.hifeful.mealmania.domain.model.Ingredient
import com.hifeful.mealmania.presentation.util.loadUrl

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    var ingredients: List<Ingredient> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder =
        IngredientsViewHolder(
            ItemIngredientBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size

    inner class IngredientsViewHolder(
        private val binding: ItemIngredientBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: Ingredient) {
            with(binding) {
                ingredientName.text = ingredient.name
                ingredientMeasure.text = ingredient.measure
                imageViewIngredient.loadUrl(ingredient.imageUrl)
            }
        }
    }
}