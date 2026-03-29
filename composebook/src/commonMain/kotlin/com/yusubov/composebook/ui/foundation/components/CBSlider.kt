package com.yusubov.composebook.ui.foundation.components

import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        valueRange = valueRange,
        colors = SliderDefaults.colors(
            thumbColor = ComposeBookTheme.colors.component,
            activeTrackColor = ComposeBookTheme.colors.component,
            inactiveTrackColor = ComposeBookTheme.colors.surfaceVariant
        )
    )
}