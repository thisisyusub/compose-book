package com.yusubov.composebook.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.navigation.DirectoryNode
import com.yusubov.composebook.core.navigation.NavigationNode
import com.yusubov.composebook.core.navigation.NavigationState
import com.yusubov.composebook.core.navigation.UseCaseNode

@Composable
internal fun NavigationMenuView(
    state: NavigationState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier.fillMaxHeight().background(MaterialTheme.colorScheme.surface),
    ) {
        SearchField(state)
        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

        if (state.searchQuery.isNotBlank()) {
            SearchResultsView(state, { state.select(it) })
        } else {
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                renderNodes(state.rootNodes, 0, state)
            }
        }
    }
}

private fun LazyListScope.renderNodes(
    nodes: List<NavigationNode>,
    indent: Int,
    state: NavigationState,
) {
    for (node in nodes) {
        when (node) {
            is DirectoryNode -> {
                item(key = node.key) {
                    DirectoryRowItem(
                        name = node.name,
                        indent = indent,
                        expanded = node.expanded,
                        onClick = { state.toggleExpanded(node) },
                    )
                }
                if (node.expanded) {
                    renderNodes(node.children, indent + 1, state)
                }
            }

            is UseCaseNode -> {
                item(key = node.key) {
                    UseCaseRowItem(
                        useCase = node.path.useCase,
                        indent = indent,
                        selected = node.path == state.selectedPath,
                        onSelect = { state.select(node.path) },
                    )
                }
            }
        }
    }
}