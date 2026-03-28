package com.yusubov.composebook.ui.panels.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.navigation.NavRow
import com.yusubov.composebook.core.navigation.NavigationState

@Composable
internal fun TreeView(state: NavigationState) {
    val rows = state.visibleRows

    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        items(rows, key = { it.key }) { row ->
            when (row) {
                is NavRow.CategoryRow -> CategoryRowView(
                    row.name,
                    row.depth,
                    state.isExpanded(row.key),
                ) {
                    state.toggleExpanded(row.key)
                }

                is NavRow.ComponentRow -> ComponentRowView(
                    row.component,
                    row.depth,
                    state.isExpanded(row.key)
                ) {
                    state.toggleExpanded(row.key)
                }

                is NavRow.UseCaseRow -> UseCaseRowView(
                    row.useCase,
                    row.depth,
                    row.path == state.selectedPath
                ) {
                    state.select(row.path)
                }
            }
        }
    }
}