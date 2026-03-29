package com.yusubov.composebook.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.knobs.KnobScope

@Composable
internal fun KnobListView(
    knobScope: KnobScope,
    modifier: Modifier = Modifier,
) {
    val knobs = knobScope.knobs.values.toList()

    if (knobs.isEmpty()) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                "No knobs registered.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    } else {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(knobs, key = { it.label }) { knob ->
                knob.Render()
            }
        }
    }
}