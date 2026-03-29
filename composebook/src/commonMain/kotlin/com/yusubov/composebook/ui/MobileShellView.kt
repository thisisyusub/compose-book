package com.yusubov.composebook.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.core.navigation.NavigationState
import com.yusubov.composebook.ui.foundation.components.CBNavigationBar
import com.yusubov.composebook.ui.foundation.components.CBNavigationBarItem
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme
import com.yusubov.composebook.ui.views.AddonListView
import com.yusubov.composebook.ui.views.HalfExpandableBottomSheet
import com.yusubov.composebook.ui.views.KnobListView
import com.yusubov.composebook.ui.views.NavigationMenuView
import com.yusubov.composebook.ui.views.UseCaseView

@Composable
internal fun MobileShellView(
    knobScope: KnobScope,
    navigationState: NavigationState,
    addonList: List<Addon>,
) {
    var showMenuBottomSheet by remember { mutableStateOf(false) }
    var showKnobBottomSheet by remember { mutableStateOf(false) }
    var showAddOnsBottomSheet by remember { mutableStateOf(false) }

    if (showMenuBottomSheet) {
        HalfExpandableBottomSheet(onDismiss = { showMenuBottomSheet = false }) {
            NavigationMenuView(state = navigationState)
        }
    }

    if (showKnobBottomSheet) {
        HalfExpandableBottomSheet(onDismiss = { showKnobBottomSheet = false }) {
            KnobListView(knobScope)
        }
    }

    if (showAddOnsBottomSheet) {
        HalfExpandableBottomSheet(onDismiss = { showAddOnsBottomSheet = false }) {
            AddonListView(addonList = addonList)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(ComposeBookTheme.colors.background),
    ) {
        Box(modifier = Modifier.weight(1f)) {
            UseCaseView(
                knobScope = knobScope,
                navigationState = navigationState,
                addonList = addonList,
            )
        }

        CBNavigationBar {
            CBNavigationBarItem(
                selected = showMenuBottomSheet,
                onClick = { showMenuBottomSheet = true },
                icon = Icons.Default.Menu,
                label = "Menu",
            )
            CBNavigationBarItem(
                selected = showKnobBottomSheet,
                onClick = { showKnobBottomSheet = true },
                icon = Icons.Default.Tune,
                label = "Knobs",
            )
            CBNavigationBarItem(
                selected = showAddOnsBottomSheet,
                onClick = { showAddOnsBottomSheet = true },
                icon = Icons.Default.Extension,
                label = "Add-ons",
            )
        }
    }
}