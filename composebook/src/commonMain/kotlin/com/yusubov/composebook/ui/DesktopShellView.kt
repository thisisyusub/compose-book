package com.yusubov.composebook.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.core.navigation.NavigationState
import com.yusubov.composebook.ui.components.ConfigPanel
import com.yusubov.composebook.ui.components.NavigationMenuView
import com.yusubov.composebook.ui.components.UseCaseView

@Composable
internal fun DesktopShellView(
    knobScope: KnobScope,
    navigationState: NavigationState,
    addonList: List<Addon>,
) {
    Row(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // Left: Navigation
        NavigationMenuView(
            modifier = Modifier.width(280.dp),
            state = navigationState,
        )
        VerticalDivider()

        // Center: Preview
        UseCaseView(
            modifier = Modifier.weight(1f),
            knobScope = knobScope,
            navigationState = navigationState,
            addonList = addonList,
        )
        VerticalDivider()

        ConfigPanel(
            knobScope = knobScope,
            addonList = addonList,
            modifier = Modifier.width(300.dp),
        )
    }
}