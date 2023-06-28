package com.hifeful.mealmania.presentation.myMeals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.hifeful.mealmania.R
import com.hifeful.mealmania.databinding.FragmentMyMealsBinding
import com.hifeful.mealmania.presentation.details.MealDetailsFragment
import com.hifeful.mealmania.presentation.util.SpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyMealsFragment : Fragment() {

    private var _binding: FragmentMyMealsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyMealsViewModel by viewModels()

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

        setUpViews()
        observeViewModel()
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

        favouriteMealsAdapter = FavouriteMealsAdapter(
            onClickMeal = { attachMealDetailsFragment(it) }
        ).apply {
            registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    binding.recyclerViewFavouriteMeals.smoothScrollToPosition(0)

                }
            })
        }
        binding.recyclerViewFavouriteMeals.apply {
            adapter = favouriteMealsAdapter
        }
    }

    private fun setUpLoading() {
        with(viewModel.events) {
            trySend(MyMealsEvent.LoadRecentMeals)
            trySend(MyMealsEvent.LoadFavouriteMeals)
        }
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

    private fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    binding.bind(it, recentMealsAdapter, favouriteMealsAdapter)
                }
            }
        }
    }
}