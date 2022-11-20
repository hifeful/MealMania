package com.hifeful.mealmania.presentation.myMeals

import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings

class MyMealsFragmentBindings(
    view: MyMealsFragment,
    private val feature: MyMealsFeature
) : AndroidBindings<MyMealsFragment>(view) {

    override fun setup(view: MyMealsFragment) {
        binder.bind(feature to view using MyMealsViewStateTransformer())
        binder.bind(view to feature using MyMealsUiEventTransformer())
    }
}