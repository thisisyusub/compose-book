package com.yusubov.composebook.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.ui.foundation.components.CBTab
import com.yusubov.composebook.ui.foundation.components.CBTabRow
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

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
        modifier = modifier
            .fillMaxHeight()
            .background(ComposeBookTheme.colors.surface),
    ) {
        if (tabs.size > 1) {
            CBTabRow {
                tabs.forEachIndexed { i, title ->
                    CBTab(
                        selected = selectedTab == i,
                        onClick = { selectedTab = i },
                        text = title,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        } else {
            // Fallback divider if there's only 1 or 0 tabs
            HorizontalDivider(
                color = ComposeBookTheme.colors.border,
                thickness = ComposeBookTheme.sizes.divider
            )
        }

        when (selectedTab) {
            0 -> KnobListView(knobScope)
            1 -> AddonListView(addonList = addonList)
        }
    }
}