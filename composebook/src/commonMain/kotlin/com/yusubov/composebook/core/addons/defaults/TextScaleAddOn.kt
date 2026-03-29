package com.yusubov.composebook.core.addons.defaults

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.core.utils.formatFloat
import com.yusubov.composebook.ui.foundation.components.CBSlider
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme
import kotlin.math.roundToInt

internal class TextScaleAddon(initialScale: Float = 1f) : Addon {
    override val name = "Text Scale"
    var scale: Float by mutableStateOf(initialScale)

    @Composable
    override fun PanelContent() {
        TextScaleAddonPanel(scale) { scale = it }
    }

    @Composable
    override fun Wrap(content: @Composable () -> Unit) {
        val density = LocalDensity.current
        val newDensity = Density(density = density.density, fontScale = scale)

        CompositionLocalProvider(LocalDensity provides newDensity) {
            content()
        }
    }
}

@Composable
internal fun TextScaleAddonPanel(
    scale: Float,
    onScaleChanged: (Float) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.sm)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Scale: ${(scale * 100).roundToInt()}%",
                style = ComposeBookTheme.typography.bodySmall,
                color = ComposeBookTheme.colors.textSecondary,
            )
            Text(
                text = "${formatFloat(scale, 2)}x",
                style = ComposeBookTheme.typography.bodySmall,
                color = ComposeBookTheme.colors.textSecondary,
            )
        }
        CBSlider(
            value = scale,
            onValueChange = onScaleChanged,
            valueRange = 0.5f..3f,
            steps = 9,
        )
    }
}