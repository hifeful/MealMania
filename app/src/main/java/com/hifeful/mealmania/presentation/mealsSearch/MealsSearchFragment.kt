package com.hifeful.mealmania.presentation.mealsSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.hifeful.mealmania.R
import com.hifeful.mealmania.common.ObservableSourceFragment
import com.hifeful.mealmania.databinding.FragmentMealsSearchBinding
import com.hifeful.mealmania.presentation.util.SpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.functions.Consumer
import javax.inject.Inject

@AndroidEntryPoint
class MealsSearchFragment :
    ObservableSourceFragment<MealsSearchUiEvent>(),
    Consumer<MealsSearchViewState> {

    @Inject
    lateinit var mealsSearchFeature: MealsSearchFeature

    private var _binding: FragmentMealsSearchBinding? = null
    private val binding get() = _binding!!

    private var foundMealsAdapter: FoundMealsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bindings = MealsSearchFragmentBindings(this, mealsSearchFeature)
        bindings.setup(this)

        hideActionBar()
        hideBottomNavigation()
        setUpViews()
        handleArguments()
    }

    private fun setUpViews() {
        binding.mealsSearchView.searchListener = { query -> 
            onNext(MealsSearchUiEvent.SearchMeals(query))
        }

        foundMealsAdapter = FoundMealsAdapter()
        binding.recyclerViewFoundMeals.apply {
            adapter = foundMealsAdapter
            addItemDecoration(
                SpacingItemDecoration(
                    context,
                    displayMode = SpacingItemDecoration.VERTICAL,
                    marginValue = R.dimen.margin_16dp
                )
            )
        }
    }

    private fun handleArguments() {
        arguments?.getString(SEARCH_QUERY_KEY)?.let {
            binding.mealsSearchView.setText(it)
            onNext(MealsSearchUiEvent.SearchMeals(it))
        }
    }

    override fun accept(viewState: MealsSearchViewState) {
        binding.bind(viewState, foundMealsAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        showBottomNavigation()
        showActionBar()
        _binding = null
    }

    companion object {

        private const val SEARCH_QUERY_KEY = "SEARCH_QUERY"

        fun getInstance(query: String): MealsSearchFragment {
            return MealsSearchFragment().apply {
                arguments = bundleOf(SEARCH_QUERY_KEY to query)
            }
        }
    }
}