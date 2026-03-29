package com.yusubov.composebook.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.ui.foundation.components.CBCard
import com.yusubov.composebook.ui.foundation.components.CBText
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun AddonListView(
    addonList: List<Addon>,
    modifier: Modifier = Modifier,
) {
    if (addonList.isEmpty()) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CBText(
                text = "No add-ons configured.",
                style = ComposeBookTheme.typography.bodySmall,
                color = ComposeBookTheme.colors.textSecondary,
            )
        }
    } else {
        val arrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.sm)

        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(ComposeBookTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.lg),
        ) {
            items(addonList, key = { it.name }) { addon ->
                CBCard(modifier = Modifier.fillMaxWidth()) {
                    Column(verticalArrangement = arrangement) {
                        CBText(
                            text = addon.name,
                            style = ComposeBookTheme.typography.heading,
                        )
                        addon.PanelContent()
                    }
                }
            }
        }
    }
}