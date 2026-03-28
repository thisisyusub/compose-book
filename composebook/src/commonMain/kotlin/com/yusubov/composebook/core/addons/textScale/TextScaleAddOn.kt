package com.yusubov.composebook.core.addons.textScale

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.yusubov.composebook.core.addons.AddOn

class TextScaleAddon(initialScale: Float = 1f) : AddOn {
    override val name = "Text Scale"
    var scale: Float by mutableStateOf(initialScale)

    @Composable
    override fun PanelContent() {
        TextScaleAddonPanel(scale) { scale = it }
    }

    @Composable
    override fun Wrap(content: @Composable () -> Unit) {
        val density = LocalDensity.current

        CompositionLocalProvider(
            LocalDensity provides
                    Density(
                        density = density.density,
                        fontScale = density.fontScale * scale,
                    )
        ) {
            content()
        }
    }
}