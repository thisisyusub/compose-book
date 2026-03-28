package com.yusubov.composebook.ui.panels.knob


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusubov.composebook.core.addons.AddOn
import com.yusubov.composebook.core.knobs.KnobScope

@Composable
internal fun KnobPanel(
    knobScope: KnobScope,
    addons: List<AddOn>,
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableStateOf(0) }

    val tabs = buildList {
        add("Knobs")
        if (addons.isNotEmpty()) add("Addons")
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        if (tabs.size > 1) {
            TabRow(
                selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                tabs.forEachIndexed { i, title ->
                    Tab(
                        selected = selectedTab == i,
                        onClick = { selectedTab = i },
                        text = {
                            Text(
                                title,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium
                            )
                        },
                    )
                }
            }
        } else {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            ) {
                Text(
                    "Knobs",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

        when (selectedTab) {
            0 -> KnobsList(knobScope)
            1 -> AddonsList(addons)
        }
    }
}

@Composable
private fun KnobsList(scope: KnobScope) {
    val knobs = scope.knobs.values.toList()

    if (knobs.isEmpty()) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                "No knobs registered.\nAdd knobs in your use-case.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { }) {
                        Icon(
                            Icons.Default.Refresh,
                            null,
                            Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text("Reset All", fontSize = 12.sp)
                    }
                }
            }

            items(knobs, key = { it.label }) { knob ->
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {

                    Text(
                        knob.label,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                    if (knob.description.isNotBlank()) {
                        Text(
                            knob.description,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    knob.Render()
                }
            }
        }
    }
}

@Composable
private fun AddonsList(addons: List<AddOn>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(addons) { addon ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(
                        alpha = 0.5f
                    )
                )
            ) {
                Column(
                    Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        addon.name, style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary
                    )
                    addon.PanelContent()
                }
            }
        }
    }
}