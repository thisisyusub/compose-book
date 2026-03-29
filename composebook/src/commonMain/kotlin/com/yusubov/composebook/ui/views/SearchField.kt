package com.yusubov.composebook.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.navigation.NavigationState
import com.yusubov.composebook.ui.foundation.components.CBIcon
import com.yusubov.composebook.ui.foundation.components.CBInputField
import com.yusubov.composebook.ui.foundation.components.CBText
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun SearchField(
    state: NavigationState,
    modifier: Modifier = Modifier,
) {
    CBInputField(
        value = state.searchQuery,
        onValueChange = { state.searchQuery = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(ComposeBookTheme.spacing.md),
        placeholder = {
            CBText(
                text = "Search use-cases...",
                style = ComposeBookTheme.typography.bodySmall,
                color = ComposeBookTheme.colors.textSecondary
            )
        },
        leadingIcon = {
            CBIcon(
                imageVector = Icons.Default.Search,
                tint = ComposeBookTheme.colors.textSecondary
            )
        },
        trailingIcon = {
            if (state.searchQuery.isNotBlank()) {
                Box(modifier = Modifier.clickable { state.searchQuery = "" }) {
                    CBIcon(
                        imageVector = Icons.Default.Close,
                        size = ComposeBookTheme.sizes.iconSm,
                        tint = ComposeBookTheme.colors.textSecondary
                    )
                }
            }
        }
    )
}