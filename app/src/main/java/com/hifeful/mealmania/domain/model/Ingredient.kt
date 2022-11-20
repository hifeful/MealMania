package com.hifeful.mealmania.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    val name: String,
    val measure: String,
    val imageUrl: String
) : Parcelable