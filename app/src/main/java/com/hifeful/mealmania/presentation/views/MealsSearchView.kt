package com.hifeful.mealmania.presentation.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import com.hifeful.mealmania.databinding.ViewMealSearchBinding
import com.hifeful.mealmania.presentation.util.focusAndShowKeyboard
import com.hifeful.mealmania.presentation.util.hideKeyboard
import com.hifeful.mealmania.presentation.util.inflateCustomView
import com.hifeful.mealmania.presentation.util.setDebouncedOnClickListener

class MealsSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : RelativeLayout(context, attrs) {

    var searchListener: ((String) -> Unit)? = null

    private val binding = inflateCustomView(ViewMealSearchBinding::inflate)

    private val textListener = object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(text: Editable) {
            changeClearIconVisibility(text.toString())
        }
    }

    init {
        handleEditText()
    }

    override fun onDetachedFromWindow() {
        binding.editTextSearch.removeTextChangedListener(textListener)
        super.onDetachedFromWindow()
    }

    fun setOnBackPressListener(listener: () -> Unit) {
        binding.imageViewBack.setDebouncedOnClickListener { listener.invoke() }
    }

    fun setText(query: String?) {
        binding.editTextSearch.setText(query)
    }

    fun setHint(hint: String?) {
        binding.editTextSearch.hint = hint
    }

    private fun handleEditText() {
        with(binding.editTextSearch) {
            addTextChangedListener(textListener)
            setOnEditorActionListener { _, actionId, _ ->
                if (isSearchKeyCode(actionId)) {
                    searchListener?.invoke(text.toString())
                    binding.editTextSearch.hideKeyboard()
                    true
                } else {
                    false
                }
            }
            postDelayed(
                { focusAndShowKeyboard() },
                DELAY_KEYBOARD_OPEN
            )
        }

        binding.imageViewClearText.setDebouncedOnClickListener { onCleared() }
    }

    private fun changeClearIconVisibility(searchedText: String?) {
        binding.imageViewClearText.isVisible = isClearTextVisible(searchedText)
    }

    private fun onCleared() {
        binding.editTextSearch.setText("")
        binding.editTextSearch.focusAndShowKeyboard()
    }

    private fun isClearTextVisible(searchedText: String?): Boolean = (searchedText?.length ?: 0) > 0

    private fun isSearchKeyCode(actionId: Int): Boolean = actionId == EditorInfo.IME_ACTION_SEARCH

    companion object {

        private const val DELAY_KEYBOARD_OPEN = 200L
    }
}