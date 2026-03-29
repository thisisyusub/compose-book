package com.yusubov.composebook.ui.foundation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalComposeBookSizes = compositionLocalOf { ComposeBookSizes() }

@Immutable
internal data class ComposeBookSizes(
    val iconSm: Dp = 14.dp,
    val iconMd: Dp = 18.dp,
    val iconLg: Dp = 24.dp,
    val inputHeight: Dp = 40.dp,
    val toggleWidth: Dp = 44.dp,
    val toggleHeight: Dp = 24.dp,
    val toggleThumb: Dp = 18.dp,
    val sliderTrackHeight: Dp = 4.dp,
    val sliderThumb: Dp = 16.dp,
    val divider: Dp = 1.dp,
    val borderWidth: Dp = 1.dp,
    val borderWidthFocused: Dp = 1.5.dp,
    val indicator: Dp = 6.dp,
    val navigationWidth: Dp = 280.dp,
    val configWidth: Dp = 300.dp,
)

