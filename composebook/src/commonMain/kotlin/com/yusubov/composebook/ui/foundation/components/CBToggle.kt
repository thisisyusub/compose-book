package com.yusubov.composebook.ui.foundation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val trackColor by animateColorAsState(
        targetValue = if (checked) ComposeBookTheme.colors.componentActive
        else ComposeBookTheme.colors.surfaceVariant,
        label = "TrackColor"
    )

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) {
            ComposeBookTheme.sizes.toggleWidth -
                    ComposeBookTheme.sizes.toggleThumb -
                    ComposeBookTheme.spacing.xs
        } else {
            ComposeBookTheme.spacing.xs
        },
        animationSpec = tween(150),
        label = "ThumbOffset"
    )

    Box(
        modifier = modifier
            .size(
                width = ComposeBookTheme.sizes.toggleWidth,
                height = ComposeBookTheme.sizes.toggleHeight,
            )
            .clip(RoundedCornerShape(ComposeBookTheme.radii.pill))
            .background(trackColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onCheckedChange(!checked) },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(ComposeBookTheme.sizes.toggleThumb)
                .clip(RoundedCornerShape(ComposeBookTheme.radii.pill))
                .background(ComposeBookTheme.colors.onComponentActive)
        )
    }
}