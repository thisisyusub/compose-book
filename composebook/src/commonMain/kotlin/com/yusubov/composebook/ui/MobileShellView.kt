package com.yusubov.composebook.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.core.navigation.NavigationState
import com.yusubov.composebook.ui.components.HalfExpandableBottomSheet
import com.yusubov.composebook.ui.components.KnobListView
import com.yusubov.composebook.ui.components.NavigationMenuView
import com.yusubov.composebook.ui.components.UseCaseView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MobileShellView(
    knobScope: KnobScope,
    navigationState: NavigationState,
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
            // TODO: add-ons will be here
        }
    }

    Column(
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    ) {
        Surface(modifier = Modifier.weight(1f)) {
            UseCaseView(
                knobScope = knobScope,
                navigationState =  navigationState,
            )
        }

        NavigationBar {
            NavigationBarItem(
                selected = showMenuBottomSheet,
                onClick = { showMenuBottomSheet = true },
                icon = { Icon(Icons.Default.Menu, contentDescription = "Menu") },
                label = { Text("Menu") },
            )
            NavigationBarItem(
                selected = showKnobBottomSheet,
                onClick = { showKnobBottomSheet = true },
                icon = { Icon(Icons.Default.Tune, contentDescription = "Knobs") },
                label = { Text("Knobs") },
            )
            NavigationBarItem(
                selected = showAddOnsBottomSheet,
                onClick = { showAddOnsBottomSheet = true },
                icon = { Icon(Icons.Default.Extension, contentDescription = "Add-ons") },
                label = { Text("Add-ons") },
            )
        }
    }
}
