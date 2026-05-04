package com.yusubov.composebook.core.addons.defaults

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.ui.foundation.components.CBText
import com.yusubov.composebook.ui.foundation.components.CBToggle
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

internal val LocalRecompositionTrackingEnabled = staticCompositionLocalOf { false }

internal class RecompositionTrackerAddOn: Addon {
    override val name: String
        get() = "Recomposition Tracker"

    private var isEnabled: Boolean by mutableStateOf(false)

    @Composable
    override fun PanelContent() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CBText(
                text = "Show recompositions",
                style = ComposeBookTheme.typography.bodySmall,
                color = ComposeBookTheme.colors.textSecondary,
            )
            CBToggle(
                checked = isEnabled,
                onCheckedChange = { isEnabled = it }
            )
        }
    }

    @Composable
    override fun Wrap(content: @Composable (() -> Unit)) {
        CompositionLocalProvider(
            LocalRecompositionTrackingEnabled provides isEnabled
        ) {
            content()
        }
    }
}