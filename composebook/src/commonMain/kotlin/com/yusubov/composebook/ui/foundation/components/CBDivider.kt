package com.yusubov.composebook.ui.foundation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBHorizontalDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(ComposeBookTheme.sizes.divider)
            .background(ComposeBookTheme.colors.border)
    )
}

@Composable
internal fun CBVerticalDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(ComposeBookTheme.sizes.divider)
            .background(ComposeBookTheme.colors.border)
    )
}