package com.yusubov.composebook.ui.foundation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalComposeBookColors = compositionLocalOf { LightColors }

@Immutable
internal data class ComposeBookColors(
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val text: Color,
    val textSecondary: Color,
    val border: Color,
    val borderFocused: Color,
    val component: Color,
    val componentActive: Color,
    val onComponent: Color,
    val onComponentActive: Color,
)

internal val LightColors = ComposeBookColors(
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF8F8F8),
    surfaceVariant = Color(0xFFF0F0F0),
    text = Color(0xFF0A0A0A),
    textSecondary = Color(0xFF6E6E6E),
    border = Color(0xFFDDDDDD),
    borderFocused = Color(0xFF0A0A0A),
    component = Color(0xFF0A0A0A),
    componentActive = Color(0xFF000000),
    onComponent = Color(0xFFFFFFFF),
    onComponentActive = Color(0xFFFFFFFF),
)

internal val DarkColors = ComposeBookColors(
    background = Color(0xFF0A0A0A),
    surface = Color(0xFF141414),
    surfaceVariant = Color(0xFF1E1E1E),
    text = Color(0xFFEAEAEA),
    textSecondary = Color(0xFF8A8A8A),
    border = Color(0xFF2E2E2E),
    borderFocused = Color(0xFFEAEAEA),
    component = Color(0xFFEAEAEA),
    componentActive = Color(0xFFFFFFFF),
    onComponent = Color(0xFF0A0A0A),
    onComponentActive = Color(0xFF000000),
)