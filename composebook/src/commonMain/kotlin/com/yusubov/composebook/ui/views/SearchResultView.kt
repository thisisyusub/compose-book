package com.yusubov.composebook.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.yusubov.composebook.core.models.NavigationPath
import com.yusubov.composebook.core.navigation.NavigationState
import com.yusubov.composebook.core.navigation.SearchEntry
import com.yusubov.composebook.ui.foundation.components.CBText
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun SearchResultsView(
    state: NavigationState,
    onSelect: (NavigationPath) -> Unit,
    modifier: Modifier = Modifier,
) {
    val results = state.searchResults

    if (results.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(ComposeBookTheme.spacing.xl), // 32.dp
            contentAlignment = Alignment.Center,
        ) {
            CBText(
                text = "No results found",
                style = ComposeBookTheme.typography.bodySmall,
                color = ComposeBookTheme.colors.textSecondary,
            )
        }
    } else {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(ComposeBookTheme.spacing.sm), // 8.dp
            verticalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.xs), // 2-4.dp
        ) {
            items(results, key = { it.path.fullPath }) { entry ->
                SearchResultItem(
                    entry = entry,
                    selected = entry.path == state.selectedPath,
                    onClick = { onSelect(entry.path) },
                )
            }
        }
    }
}

@Composable
private fun SearchResultItem(
    entry: SearchEntry,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(ComposeBookTheme.radii.md))
            .background(
                if (selected) ComposeBookTheme.colors.surfaceVariant
                else Color.Transparent
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = ComposeBookTheme.spacing.md, // 12.dp
                vertical = ComposeBookTheme.spacing.sm    // 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            CBText(
                text = entry.path.useCase.name,
                style = ComposeBookTheme.typography.body,
                color = if (selected) ComposeBookTheme.colors.component
                else ComposeBookTheme.colors.text,
            )
            CBText(
                text = entry.path.directories.joinToString(" > "),
                style = ComposeBookTheme.typography.caption,
                color = ComposeBookTheme.colors.textSecondary,
            )
        }
    }
}