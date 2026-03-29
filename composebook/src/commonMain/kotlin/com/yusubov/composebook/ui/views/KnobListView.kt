package com.yusubov.composebook.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.ui.foundation.components.CBText
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun KnobListView(
    knobScope: KnobScope,
    modifier: Modifier = Modifier,
) {
    val knobs = knobScope.knobs.values.toList()

    if (knobs.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CBText(
                text = "No knobs registered.",
                style = ComposeBookTheme.typography.bodySmall,
                color = ComposeBookTheme.colors.textSecondary,
            )
        }
    } else {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(ComposeBookTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.lg),
        ) {
            items(knobs, key = { it.label }) { knob ->
                knob.Render()
            }
        }
    }
}