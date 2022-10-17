package com.hifeful.mealmania.presentation.home

import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings

class HomeFragmentBindings(
    view: HomeFragment,
    private val feature: HomeFeature
) : AndroidBindings<HomeFragment>(view) {

    override fun setup(view: HomeFragment) {
        binder.bind(feature to view using HomeViewStateTransformer())
        binder.bind(view to feature using HomeUiEventTransformer())
    }
}