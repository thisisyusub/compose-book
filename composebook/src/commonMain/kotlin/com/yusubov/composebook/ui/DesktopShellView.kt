package com.yusubov.composebook.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.core.navigation.NavigationState
import com.yusubov.composebook.ui.foundation.components.CBVerticalDivider
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme
import com.yusubov.composebook.ui.views.ConfigPanel
import com.yusubov.composebook.ui.views.NavigationMenuView
import com.yusubov.composebook.ui.views.UseCaseView

@Composable
internal fun DesktopShellView(
    knobScope: KnobScope,
    navigationState: NavigationState,
    addonList: List<Addon>,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(ComposeBookTheme.colors.background)
    ) {
        NavigationMenuView(
            modifier = Modifier.width(ComposeBookTheme.sizes.navigationWidth),
            state = navigationState,
        )
        CBVerticalDivider()
        UseCaseView(
            modifier = Modifier.weight(1f),
            knobScope = knobScope,
            navigationState = navigationState,
            addonList = addonList,
        )
        CBVerticalDivider()
        ConfigPanel(
            knobScope = knobScope,
            addonList = addonList,
            modifier = Modifier.width(ComposeBookTheme.sizes.configWidth), // 300.dp
        )
    }
}