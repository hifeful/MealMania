package com.hifeful.mealmania.presentation.myMeals

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.hifeful.mealmania.domain.model.Meal
import com.hifeful.mealmania.domain.repository.MealsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * 💢 Questions
 * 🟡 Please, answer on them before moving further and after changes would be made
 * 1️⃣ How the configuration changes are handled with Feature (if so)?
 * 2️⃣ Can we have MVI using ViewModel or Presenter?
 * 3️⃣ Could the race conditions issue happen
 *
 * IMO, I don't think that the implementation of MVI should be like this.
 * The architecture is the very crucial pilar for any app.
 * Usually, the implementation is unique for a project as there is no the strict rules how to do it.
 *
 * 1️⃣ You are relying on the third-party framework which is used presumably in the closed group
 *    of people. Meaning we are as externals won't know about actual motivation and appetite of
 *    existed functionality and further updates of the framework.
 *    Doing so, we explicitly agree with those limitation where we won;t be able to customize it.
 *    The workarounds would appear. Plus, imagine the owner company is closed, and the framework
 *    won't be supported. So, your app become outdated pretty fast. 🧐
 *
 * 2️⃣ Years ago, I've used Mavericks framework in one of pet projects. But there we decided
 *    to do so as the Kotlin language wasn't powerful at that moment. But now it's pretty stable with
 *    having Flows and Channels. Or... it's possible to use Rx instead where we have Observable and
 *    PublishSubject. It's up to you what to use - Rx or Kotlin, but I would recommend to focus on
 *    Kotlin 😉
 *
 *    Material:
 *    💠 "Implementation drafts with using only Kotlin"
 *       https://betterprogramming.pub/all-you-need-for-mvi-is-kotlin-how-to-reduce-without-reducer-5e986856610f
 *
 *    💢 But... please, check the second material as it would prevent writing a wrong solution
 *    💠 "THIS Is the #1 Clean Code Mistake"
 *       https://www.youtube.com/watch?v=jYetmv0xRRI
 *
 *    🙏 We shouldn't use base classes in all costs
 *
 * 3️⃣ Having Feature instead of ViewModel/Presenter makes confuse 😥. Usually, we are as an Android
 *    engineers prefer work with the intermediate presentation component - ViewModel or Presenter only.
 *    I don't think it's a good thing to keep alive as it increases the learning curve.
 *    We should keep things as simple as possible. So, we can-should have VM or Presenter with
 *    the implementation of MVI concept. Try to consider it as an upgrade/level-up of VM or Presenter.
 *
 *    Material:
 *    💠 Not theoretical comparison of MVVM and MVI
 *       https://www.youtube.com/watch?v=cnU2zMnmmpg
 *
 * 4️⃣ Regarding the naming convention we should stick to Event, State, SideEffect (if needed)
 */
class MyMealsFeature @Inject constructor(
    initialState: State,
    actor: Actor<State, Wish, Effect>,
    reducer: Reducer<State, Effect>
) : ActorReducerFeature<MyMealsFeature.Wish,
        MyMealsFeature.Effect,
        MyMealsFeature.State,
        Nothing>(initialState = initialState, actor = actor, reducer = reducer) {

    // Should be extracted to outside 😉
    // The same for Wish, Effect, etc.
    data class State(
        // State should expose UI models or primitive types.
        // Or UI model that holds the domain model
        val recentMeals: List<Meal>? = null,
        val favouriteMeals: List<Meal>? = null
    )

    sealed class Wish {
        object LoadRecentMeals : Wish()
        object LoadFavouriteMeals : Wish()
    }

    sealed class Effect {
        data class LoadedRecentMeals(val meals: List<Meal>) : Effect()
        data class LoadedFavouriteMeals(val meals: List<Meal>) : Effect()
    }

    // Wish and Effect types seem are the same from the syntax sense at least.
    // In other words, kinda of duplication that causes the complexity.
    class ActorImpl(
        private val mealsRepository: MealsRepository
    ) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, action: Wish): Observable<out Effect> =
            when (action) {
                is Wish.LoadRecentMeals -> mealsRepository.getRecentMeals()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.LoadedRecentMeals(it) }
                is Wish.LoadFavouriteMeals -> mealsRepository.getFavouriteMeals()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { Effect.LoadedFavouriteMeals(it) }
            }
    }

    class ReducerImpl : Reducer<State, Effect> {

        override fun invoke(state: State, effect: Effect): State =
            when (effect) {
                is Effect.LoadedRecentMeals -> state.copy(recentMeals = effect.meals)
                is Effect.LoadedFavouriteMeals -> state.copy(favouriteMeals = effect.meals)
            }

    }
}