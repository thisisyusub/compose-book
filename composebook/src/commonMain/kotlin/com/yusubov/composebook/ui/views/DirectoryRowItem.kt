package com.yusubov.composebook.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.FolderCopy
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun DirectoryRowItem(
    name: String,
    indent: Int,
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(
                start = ComposeBookTheme.spacing.sm + (ComposeBookTheme.spacing.sm * indent),
                end = ComposeBookTheme.spacing.sm,
                top = ComposeBookTheme.spacing.xs,
                bottom = ComposeBookTheme.spacing.xs,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.sm),
    ) {
        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowDown
            else Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(ComposeBookTheme.sizes.iconMd),
            tint = ComposeBookTheme.colors.textSecondary,
        )
        Icon(
            imageVector = if (expanded) Icons.Default.FolderOpen
            else Icons.Default.FolderCopy,
            contentDescription = null,
            modifier = Modifier.size(ComposeBookTheme.sizes.iconMd),
            tint = ComposeBookTheme.colors.text,
        )
        Text(
            text = name,
            style = ComposeBookTheme.typography.body,
            fontWeight = FontWeight.SemiBold,
            color = ComposeBookTheme.colors.text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}