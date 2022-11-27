package com.hifeful.mealmania.presentation.details

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.hifeful.mealmania.domain.model.Meal

data class MealDetailsViewState(
    val meal: Meal?,
    val mealLoadingError: Throwable? = null,
    val isAddedToRecent: Boolean = false,
    val isMealLoaded: Boolean = false,
    val isFavourite: Boolean? = null
) {

    fun getFavouriteIcon(context: Context): ColorStateList {
        val color = if (isFavourite != null && isFavourite) {
            android.R.color.holo_red_dark
        } else {
            android.R.color.white
        }

        return ColorStateList.valueOf(ContextCompat.getColor(context, color))
    }
}