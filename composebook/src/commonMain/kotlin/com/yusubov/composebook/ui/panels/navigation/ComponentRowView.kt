package com.yusubov.composebook.ui.panels.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusubov.composebook.core.models.Component

@Composable
internal fun ComponentRowView(
    component: Component,
    depth: Int,
    expanded: Boolean,
    onToggle: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth()
            .clickable(onClick = onToggle)
            .padding(start = (12 + depth * 16).dp, end = 12.dp, top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Icon(
            if (expanded) Icons.Default.KeyboardArrowDown
            else Icons.AutoMirrored.Filled.KeyboardArrowRight,
            null,
            Modifier.size(14.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Icon(
            Icons.Default.Widgets,
            null,
            Modifier.size(14.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Text(
            component.name,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(Modifier.weight(1f))
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
        ) {
            Text(
                "${component.useCases.size}",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 10.sp,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}