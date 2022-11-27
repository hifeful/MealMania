package com.hifeful.mealmania.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.hifeful.mealmania.R
import com.hifeful.mealmania.common.ObservableSourceFragment
import com.hifeful.mealmania.databinding.FragmentMealDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.functions.Consumer
import javax.inject.Inject

@AndroidEntryPoint
class MealDetailsFragment :
    ObservableSourceFragment<MealDetailsUiEvent>(),
    Consumer<MealDetailsViewState> {

    @Inject
    lateinit var mealDetailsFeature: MealDetailsFeature

    private var _binding: FragmentMealDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bindings = MealDetailsFragmentBindings(this, mealDetailsFeature)
        bindings.setup(this)
        setUpViews()
        arguments?.getString(MEAL_ID)?.let {
            onNext(MealDetailsUiEvent.LoadMealDetails(it))
        }
    }

    private fun setUpViews() {
        hideActionBar()
        hideBottomNavigation()

        binding.fabFavourite.setOnClickListener {
            arguments?.getString(MEAL_ID)?.let { id ->
                onNext(MealDetailsUiEvent.ClickFavourite(id))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showActionBar()
        showBottomNavigation()

        _binding = null
    }

    override fun accept(viewState: MealDetailsViewState) {
        with(viewState) {
            when {
                mealLoadingError != null -> {
                    Toast.makeText(
                        requireContext(),
                        resources.getString(R.string.product_not_found_error),
                        Toast.LENGTH_LONG
                    ).show()
                    requireActivity().supportFragmentManager.popBackStack()
                }
                isAddedToRecent.not() -> {
                    meal?.let { onNext(MealDetailsUiEvent.AddIntoRecentMeals(it)) }
                }
                isMealLoaded && isFavourite == null -> {
                    meal?.let { onNext(MealDetailsUiEvent.IsFavourite(it.id)) }
                }
                else -> {}
            }
        }
        binding.bind(viewState)
    }

    companion object {

        private const val MEAL_ID = "MEAL_ID"

        fun getInstance(id: String): MealDetailsFragment {
            return MealDetailsFragment().apply {
                arguments = bundleOf(MEAL_ID to id)
            }
        }
    }
}