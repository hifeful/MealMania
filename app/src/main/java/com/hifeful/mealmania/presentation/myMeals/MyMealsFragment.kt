package com.hifeful.mealmania.presentation.myMeals

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.hifeful.mealmania.R
import com.hifeful.mealmania.common.ObservableSourceFragment
import com.hifeful.mealmania.databinding.FragmentMyMealsBinding
import com.hifeful.mealmania.presentation.details.MealDetailsFragment
import com.hifeful.mealmania.presentation.util.SpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.functions.Consumer
import javax.inject.Inject

@AndroidEntryPoint
class MyMealsFragment : ObservableSourceFragment<MyMealsUiEvent>(), Consumer<MyMealsViewState> {

    @Inject
    lateinit var myMealsFeature: MyMealsFeature

    private var _binding: FragmentMyMealsBinding? = null
    private val binding get() = _binding!!

    private var recentMealsAdapter: RecentMealsAdapter? = null
    private var favouriteMealsAdapter: FavouriteMealsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyMealsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bindings = MyMealsFragmentBindings(this, myMealsFeature)
        bindings.setup(this)
        setUpViews()

        setUpLoading()
    }

    private fun setUpViews() {
        recentMealsAdapter = RecentMealsAdapter().apply {
            onMealClickListener = { attachMealDetailsFragment(it) }
        }
        binding.recyclerViewRecentMeals.apply {
            adapter = recentMealsAdapter
            addItemDecoration(
                SpacingItemDecoration(
                    context,
                    displayMode = SpacingItemDecoration.VERTICAL,
                    marginValue = R.dimen.margin_8dp
                )
            )
            isNestedScrollingEnabled = false
        }

        favouriteMealsAdapter = FavouriteMealsAdapter().apply {
            onMealClickListener = { attachMealDetailsFragment(it) }
        }
        binding.recyclerViewFavouriteMeals.apply {
            adapter = favouriteMealsAdapter
        }
    }

    private fun setUpLoading() {
        onNext(MyMealsUiEvent.LoadRecentMeals)
        onNext(MyMealsUiEvent.LoadFavouriteMeals)
    }

    private fun attachMealDetailsFragment(id: String) {
        parentFragmentManager.commit {
            add(R.id.fragment_container_view, MealDetailsFragment.getInstance(id))
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun accept(viewState: MyMealsViewState) {
        Log.d("MyMealsFragment", viewState.favouriteMeals.toString())
        binding.bind(viewState, recentMealsAdapter, favouriteMealsAdapter)
    }
}