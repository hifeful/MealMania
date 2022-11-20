package com.hifeful.mealmania.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(imageUrl: String) =
    Glide.with(context)
        .load(imageUrl)
        .centerCrop()
        .into(this)

fun ImageView.loadUrlCircleCrop(imageUrl: String) =
    Glide.with(context)
        .load(imageUrl)
        .circleCrop()
        .into(this)