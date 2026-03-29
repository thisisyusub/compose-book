package com.yusubov.composebook.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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
internal fun SearchField(
    state: NavigationState,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth().padding(12.dp),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        textStyle = MaterialTheme.typography.bodySmall,
        value = state.searchQuery,
        onValueChange = { state.searchQuery = it },
        placeholder = {
            Text(
                "Search use-cases...",
                style = MaterialTheme.typography.bodySmall,
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        trailingIcon = {
            if (state.searchQuery.isNotBlank()) {
                IconButton(onClick = { state.searchQuery = "" }) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                    )
                }
            }
        },
    )
}