package com.hifeful.mealmania.presentation.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hifeful.mealmania.R

class SpacingItemDecoration @JvmOverloads constructor(
    context: Context,
    private var displayMode: Int = -1,
    marginValue: Int = R.dimen.default_margin,
    private val skipFirstItem: Boolean = false,
    private val includeEdge: Boolean = true,
    private val skipFirstHorizontalItem: Boolean = false,
    private val skipLastHorizontalItem: Boolean = false,
) : RecyclerView.ItemDecoration() {

    private val spacing: Int by lazy { context.resources.getDimension(marginValue).toInt() }
    private var elevationSpacing = 0

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager
        setSpacingForDirection(view, outRect, layoutManager, position, itemCount)
    }

    private fun setSpacingForDirection(
        view: View,
        outRect: Rect,
        layoutManager: RecyclerView.LayoutManager?,
        position: Int,
        itemCount: Int,
    ) {
        if (displayMode == -1) {
            displayMode = resolveDisplayMode(layoutManager)
        }

        elevationSpacing = view.elevation.toInt()

        when (displayMode) {
            HORIZONTAL -> {
                outRect.left = if (skipFirstHorizontalItem && position == 0) 0 else spacing
                outRect.right = if (position == itemCount - 1 && skipLastHorizontalItem.not()) spacing else 0
                outRect.top = if (skipFirstItem && position == 0) 0 else elevationSpacing
                outRect.bottom = if (skipFirstItem && position == 0) 0 else elevationSpacing
            }
            VERTICAL -> {
                outRect.left = if (skipFirstItem && position == 0) 0 else spacing
                outRect.right = if (skipFirstItem && position == 0) 0 else spacing
                outRect.top = spacing
                outRect.bottom = if (position == itemCount - 1) spacing else 0
            }
            GRID -> if (layoutManager is GridLayoutManager) {

                val spanCount = layoutManager.spanCount
                val rows = itemCount / spanCount
                val column: Int = position % spanCount

                if (includeEdge) {
                    val currentColumn = layoutManager.spanSizeLookup.getSpanIndex(position, spanCount)
                    val currentRow = layoutManager.spanSizeLookup.getSpanGroupIndex(position, spanCount)

                    outRect.top = if (currentRow == 0) 0 else spacing
                    outRect.bottom = if (currentRow == rows - 1) elevationSpacing else 0
                    if (skipFirstItem && position == 0) {
                        outRect.left = 0
                        outRect.right = 0
                    } else {
                        outRect.left = spacing - currentColumn * spacing / spanCount
                        outRect.right = (currentColumn + 1) * spacing / spanCount
                    }
                } else {
                    outRect.left = column * spacing / spanCount
                    outRect.right = spacing - (column + 1) * spacing / spanCount
                    if (position >= spanCount) {
                        outRect.top = spacing
                    }
                }
            }
        }
    }

    private fun resolveDisplayMode(layoutManager: RecyclerView.LayoutManager?): Int {
        if (layoutManager is GridLayoutManager) return GRID
        return if (layoutManager?.canScrollHorizontally() == true) HORIZONTAL else VERTICAL
    }

    companion object {

        const val HORIZONTAL = 0
        const val VERTICAL = 1
        const val GRID = 2
    }
}