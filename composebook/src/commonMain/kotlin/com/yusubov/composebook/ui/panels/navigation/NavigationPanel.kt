package com.yusubov.composebook.ui.panels.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.navigation.NavigationState

@Composable
internal fun NavigationPanel(
    state: NavigationState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { state.searchQuery = it },
            placeholder = {
                Text(
                    "Search components...",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    null,
                    Modifier.size(18.dp),
                )
            },
            trailingIcon = {
                if (state.searchQuery.isNotBlank()) {
                    IconButton(
                        onClick = { state.searchQuery = "" }) {
                        Icon(
                            Icons.Default.Close,
                            "Clear",
                            Modifier.size(16.dp),
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            textStyle = MaterialTheme.typography.bodySmall,
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

        if (state.searchQuery.isNotBlank()) {
            SearchResults(state)
        } else {
            TreeView(state)
        }
    }
}