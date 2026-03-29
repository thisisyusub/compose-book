package com.yusubov.composebook.ui.foundation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.Image
import androidx.compose.ui.unit.Dp
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = ComposeBookTheme.colors.text,
    size: Dp = ComposeBookTheme.sizes.iconMd
) {
    Image(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier.size(size),
        colorFilter = ColorFilter.tint(tint)
    )
}