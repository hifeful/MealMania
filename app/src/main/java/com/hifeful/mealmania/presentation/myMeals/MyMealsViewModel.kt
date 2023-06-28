package com.hifeful.mealmania.presentation.myMeals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hifeful.mealmania.domain.repository.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMealsViewModel @Inject constructor(
    private val mealsRepository: MealsRepository
) : ViewModel() {

    val events = Channel<MyMealsEvent>(Channel.UNLIMITED)
    val state = MutableStateFlow(MyMealsState())

    init {
        handleEvents()
    }

    private fun handleEvents() {
        viewModelScope.launch {
            events.consumeAsFlow()
                .collect { event ->
                    when (event) {
                           is MyMealsEvent.LoadRecentMeals -> mealsRepository.getRecentMeals()
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map {
                                val currentState = state.value
                                currentState.copy(recentMeals = it)
                            }.forEach { emitState(it) }
                        is MyMealsEvent.LoadFavouriteMeals -> mealsRepository.getFavouriteMeals()
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map {
                                val currentState = state.value
                                currentState.copy(favouriteMeals = it)
                            }.forEach { emitState(it) }
                    }
                }
        }
    }

    private fun emitState(myMealsState: MyMealsState) {
        viewModelScope.launch {
            state.value = myMealsState
        }
    }
}