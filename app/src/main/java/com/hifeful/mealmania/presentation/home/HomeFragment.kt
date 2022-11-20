package com.hifeful.mealmania.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import com.hifeful.mealmania.R
import com.hifeful.mealmania.common.ObservableSourceFragment
import com.hifeful.mealmania.databinding.FragmentHomeBinding
import com.hifeful.mealmania.presentation.details.MealDetailsFragment
import com.hifeful.mealmania.presentation.util.SpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.functions.Consumer
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : ObservableSourceFragment<HomeUiEvent>(), Consumer<HomeViewState> {

    @Inject
    lateinit var homeFeature: HomeFeature

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var latestMealsAdapter: LatestMealsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bindings = HomeFragmentBindings(this, homeFeature)
        bindings.setup(this)

        setUpLatestMealsRecycler()
        setUpLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun accept(viewState: HomeViewState?) {
        Log.d("HomeFragment", viewState?.randomMeals?.map { it.id }.toString())
        binding.bind(viewState, latestMealsAdapter, requireActivity())
    }

    private fun setUpLoading() {
        onNext(HomeUiEvent.LoadRandomMeal)
        onNext(HomeUiEvent.LoadLatestMeals)
    }

    private fun setUpLatestMealsRecycler() {
        latestMealsAdapter = LatestMealsAdapter().apply {
            onMealClickListener = { attachMealDetailsFragment(it) }
        }
        binding.recyclerViewLatestMeals.apply {
            adapter = latestMealsAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(
                SpacingItemDecoration(
                    context,
                    displayMode = SpacingItemDecoration.GRID,
                    marginValue = R.dimen.margin_24dp
                )
            )
            isNestedScrollingEnabled = false
        }
    }

    private fun attachMealDetailsFragment(id: String) {
        parentFragmentManager.commit {
            add(R.id.fragment_container_view, MealDetailsFragment.getInstance(id))
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}