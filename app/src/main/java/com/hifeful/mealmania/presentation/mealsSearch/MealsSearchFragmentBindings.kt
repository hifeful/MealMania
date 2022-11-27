package com.hifeful.mealmania.presentation.mealsSearch

import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings

class MealsSearchFragmentBindings(
    view: MealsSearchFragment,
    private val feature: MealsSearchFeature
) : AndroidBindings<MealsSearchFragment>(view) {

    override fun setup(view: MealsSearchFragment) {
        binder.bind(feature to view using MealsSearchViewStateTransformer())
        binder.bind(view to feature using MealsSearchUiEventTransformer())
    }
}