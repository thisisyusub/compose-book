package com.yusubov.composebook.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
internal fun ComposeBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    onToggleTheme: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}