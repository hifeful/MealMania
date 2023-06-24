package com.hifeful.mealmania.presentation.myMeals

import com.hifeful.mealmania.databinding.FragmentMyMealsBinding

fun FragmentMyMealsBinding.bind(
    viewState: MyMealsViewState,
    recentMealsAdapter: RecentMealsAdapter?,
    favouriteMealsAdapter: FavouriteMealsAdapter?
) {
    recentMealsAdapter?.submitList(viewState.recentMeals)
    /**
     *  1️⃣ We shouldn't use .post or .postDelayed anymore
     *
     *     Materials:
     *     💠 "Stop Using Post/PostDelayed in Your Android Views"
     *        https://betterprogramming.pub/stop-using-post-postdelayed-in-your-android-views-9d1c8eeaadf2
     */
    recyclerViewFavouriteMeals.post {
        /**
         *  2️⃣ Might be the buggy behavior if keep it as is.
         *     For example, if the data is still loading and the call-site is executed
         *
         *     Materials:
         *     💠 "When to scroll to a certain position?"
         *        https://dev.to/aldok/how-to-scroll-recyclerview-to-a-certain-position-5ck4
         *
         *     In my personal experience, I've done in the following way:
         *     1️⃣ introduced two functions
         *
                    fun itemRangeChangedObserver(block: () -> Unit?) =
                        object : RecyclerView.AdapterDataObserver() {

                            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                                block() ?: super.onItemRangeChanged(positionStart, itemCount)
                            }
                        }

                    fun itemRangeInsertedObserver(block: () -> Unit?) =
                        object : RecyclerView.AdapterDataObserver() {

                            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                                block() ?: super.onItemRangeInserted(positionStart, itemCount)
                            }
                        }
                2️⃣ added an observer to RecyclerView.Adapter
                    RecentMealsAdapter().apply {
                        registerAdapterDataObserver(
                            itemRangeInsertedObserver { binding.content.scrollToPosition(0) }
                        )
                    }
         */
        recyclerViewFavouriteMeals.smoothScrollToPosition(0)
    }
    favouriteMealsAdapter?.submitList(viewState.favouriteMeals)
}