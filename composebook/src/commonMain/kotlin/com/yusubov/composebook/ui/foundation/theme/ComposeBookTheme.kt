package com.yusubov.composebook.ui.foundation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
internal fun ComposeBookTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (isDark) DarkColors else LightColors

    CompositionLocalProvider(
        LocalComposeBookColors provides if (isDark) DarkColors else LightColors,
        LocalComposeBookTypography provides ComposeBookTypography(
            heading = ComposeBookTypography().heading.copy(color = colors.text),
            body = ComposeBookTypography().body.copy(color = colors.text),
            bodySmall = ComposeBookTypography().bodySmall.copy(color = colors.textSecondary),
            label = ComposeBookTypography().label.copy(color = colors.text),
            caption = ComposeBookTypography().caption.copy(color = colors.textSecondary),
        ),
        LocalComposeBookSpacing provides ComposeBookSpacing(),
        LocalComposeBookRadii provides ComposeBookRadii(),
        LocalComposeBookSizes provides ComposeBookSizes(),
    ) {
        content()
    }
}

internal object ComposeBookTheme {
    val colors: ComposeBookColors
        @Composable @ReadOnlyComposable
        get() = LocalComposeBookColors.current

    val typography: ComposeBookTypography
        @Composable @ReadOnlyComposable
        get() = LocalComposeBookTypography.current

    val spacing: ComposeBookSpacing
        @Composable @ReadOnlyComposable
        get() = LocalComposeBookSpacing.current

    val radii: ComposeBookRadii
        @Composable @ReadOnlyComposable
        get() = LocalComposeBookRadii.current

    val sizes: ComposeBookSizes
        @Composable @ReadOnlyComposable
        get() = LocalComposeBookSizes.current
}