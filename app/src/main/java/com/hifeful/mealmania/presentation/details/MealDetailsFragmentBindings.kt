package com.hifeful.mealmania.presentation.details

import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings

class MealDetailsFragmentBindings(
    view: MealDetailsFragment,
    private val feature: MealDetailsFeature
) : AndroidBindings<MealDetailsFragment>(view) {

    override fun setup(view: MealDetailsFragment) {
        binder.bind(feature to view using MealDetailsViewStateTransformer())
        binder.bind(view to feature using MealDetailsUiEventTransformer())
    }
}