package com.yusubov.composebook.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.core.knobs.KnobScope

@Composable
internal fun ConfigPanel(
    knobScope: KnobScope,
    addonList: List<Addon>,
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = buildList {
        add("Knobs")
        add("Add-ons")
    }

    Column(
        modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        if (tabs.size > 1) {
            PrimaryTabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { i, title ->
                    Tab(
                        selected = selectedTab == i,
                        onClick = { selectedTab = i },
                        text = {
                            Text(
                                title,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
                            )
                        },
                    )
                }
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
        when (selectedTab) {
            0 -> KnobListView(knobScope)
            1 -> AddonListView(addonList = addonList)
        }
    }
}