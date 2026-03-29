package com.yusubov.composebook.ui.foundation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(ComposeBookTheme.colors.surface)
    ) {
        CBHorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(ComposeBookTheme.sizes.inputHeight * 1.5f),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Composable
internal fun RowScope.CBNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
) {
    val contentColor = if (selected) {
        ComposeBookTheme.colors.component
    } else {
        ComposeBookTheme.colors.textSecondary
    }

    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CBIcon(
            imageVector = icon,
            tint = contentColor,
            size = ComposeBookTheme.sizes.iconMd
        )
        Spacer(Modifier.height(ComposeBookTheme.spacing.xs))
        CBText(
            text = label,
            style = ComposeBookTheme.typography.caption,
            color = contentColor
        )
    }
}