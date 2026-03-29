package com.yusubov.composebook.ui.foundation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBTabRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(ComposeBookTheme.sizes.divider)
                .background(ComposeBookTheme.colors.border)
        )
        // The actual tabs
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            content()
        }
    }
}

@Composable
fun CBTab(
    selected: Boolean,
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    val textColor by animateColorAsState(
        targetValue = if (selected) ComposeBookTheme.colors.text
        else ComposeBookTheme.colors.textSecondary,
        label = "TabTextColor"
    )

    val indicatorColor by animateColorAsState(
        targetValue = if (selected) ComposeBookTheme.colors.component
        else Color.Transparent,
        label = "TabIndicatorColor"
    )

    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(top = ComposeBookTheme.spacing.md),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = ComposeBookTheme.typography.label,
            color = textColor,
            modifier = Modifier.padding(bottom = ComposeBookTheme.spacing.md)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(ComposeBookTheme.sizes.borderWidthFocused)
                .background(indicatorColor)
        )
    }
}