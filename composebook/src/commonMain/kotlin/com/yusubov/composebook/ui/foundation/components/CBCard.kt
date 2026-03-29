package com.yusubov.composebook.ui.foundation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(ComposeBookTheme.radii.lg),
        color = ComposeBookTheme.colors.surface,
        contentColor = ComposeBookTheme.colors.text,
        border = BorderStroke(
            width = ComposeBookTheme.sizes.borderWidth,
            color = ComposeBookTheme.colors.border
        )
    ) {
        Box(modifier = Modifier.padding(ComposeBookTheme.spacing.lg)) {
            content()
        }
    }
}