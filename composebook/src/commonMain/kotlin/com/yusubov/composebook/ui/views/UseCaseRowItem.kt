package com.yusubov.composebook.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.times
import com.yusubov.composebook.core.models.UseCase
import com.yusubov.composebook.ui.foundation.components.CBText
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun UseCaseRowItem(
    useCase: UseCase,
    indent: Int,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = ComposeBookTheme.spacing.xs)
            .clip(RoundedCornerShape(ComposeBookTheme.radii.md))
            .background(
                if (selected) ComposeBookTheme.colors.surfaceVariant
                else Color.Transparent
            )
            .clickable(onClick = onSelect)
            .padding(
                start = ComposeBookTheme.spacing.lg + (indent * ComposeBookTheme.spacing.lg),
                end = ComposeBookTheme.spacing.md,
                top = ComposeBookTheme.spacing.xs,
                bottom = ComposeBookTheme.spacing.xs
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.xs),
    ) {
        Box(
            modifier = Modifier
                .size(ComposeBookTheme.sizes.indicator)
                .background(
                    color = if (selected) ComposeBookTheme.colors.component
                    else ComposeBookTheme.colors.border,
                    shape = CircleShape
                )
        )

        CBText(
            text = useCase.name,
            style = ComposeBookTheme.typography.bodySmall,
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal,
            color = if (selected) ComposeBookTheme.colors.component
            else ComposeBookTheme.colors.text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}