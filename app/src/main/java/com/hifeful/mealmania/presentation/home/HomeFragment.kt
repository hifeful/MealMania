package com.hifeful.mealmania.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hifeful.mealmania.common.ObservableSourceFragment
import com.hifeful.mealmania.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.functions.Consumer
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : ObservableSourceFragment<HomeUiEvent>(), Consumer<HomeViewState> {

    @Inject
    lateinit var homeFeature: HomeFeature

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        onNext(HomeUiEvent.LoadRandomMeal)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun accept(viewState: HomeViewState?) {
        binding.bind(viewState)
    }
}