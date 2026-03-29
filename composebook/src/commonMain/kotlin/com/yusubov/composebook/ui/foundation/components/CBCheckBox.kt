package com.yusubov.composebook.ui.foundation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (checked) ComposeBookTheme.colors.component
        else Color.Transparent,
        label = "BgColor"
    )
    val borderColor by animateColorAsState(
        targetValue = if (checked) ComposeBookTheme.colors.component
        else ComposeBookTheme.colors.border,
        label = "BorderColor"
    )

    Row(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(ComposeBookTheme.sizes.iconLg)
                .clip(RoundedCornerShape(ComposeBookTheme.radii.sm))
                .background(backgroundColor)
                .border(
                    width = ComposeBookTheme.sizes.borderWidth,
                    color = borderColor,
                    shape = RoundedCornerShape(ComposeBookTheme.radii.sm)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = ComposeBookTheme.colors.onComponent,
                    modifier = Modifier.size(ComposeBookTheme.sizes.iconMd)
                )
            }
        }

        if (label != null) {
            Spacer(modifier = Modifier.width(ComposeBookTheme.spacing.sm))
            Text(
                text = label,
                style = ComposeBookTheme.typography.body,
                color = ComposeBookTheme.colors.text
            )
        }
    }
}