package com.yusubov.composebook.core.addons.viewport

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ViewportAddonPanel(
    viewports: List<DeviceViewport>,
    selectedIndex: Int,
    onSelected: (Int) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        viewports.forEachIndexed { index, viewport ->
            val s = index == selectedIndex
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (s) MaterialTheme.colorScheme.primaryContainer
                        else Color.Transparent,
                    )
                    .clickable { onSelected(index) }
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                RadioButton(
                    selected = s,
                    onClick = { onSelected(index) },
                    modifier = Modifier.size(20.dp)
                )
                Column {
                    Text(
                        viewport.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    if (viewport.width > 0)
                        Text(
                            "${viewport.width} × ${viewport.height}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp,
                        )
                }
            }
        }
    }
}