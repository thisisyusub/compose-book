package com.yusubov.composebook.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF6C5CE7), secondary = Color(0xFF00B894),
    surface = Color(0xFFF8F9FA), background = Color(0xFFFFFFFF),
    onSurface = Color(0xFF2D3436), surfaceVariant = Color(0xFFEEEFF5),
    onSurfaceVariant = Color(0xFF636E72), primaryContainer = Color(0xFFEDE7FF),
    outline = Color(0xFFE0E0E0),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFA29BFE), secondary = Color(0xFF00B894),
    surface = Color(0xFF1A1A2E), background = Color(0xFF16162A),
    onSurface = Color(0xFFE8E8E8), surfaceVariant = Color(0xFF252544),
    onSurfaceVariant = Color(0xFF9E9EB8), primaryContainer = Color(0xFF2D2D5E),
    outline = Color(0xFF2D2D4A),
)

@Composable
internal fun ComposeBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(colorScheme = if (darkTheme) DarkColors else LightColors, content = content)
}