package com.yusubov.composebook

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.core.navigation.NavigationState
import com.yusubov.composebook.dsl.ComposeBookConfig
import com.yusubov.composebook.ui.DesktopShellView
import com.yusubov.composebook.ui.MobileShellView
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

private const val DESKTOP_BREAKPOINT = 900

@Composable
fun ComposeBook(
    config: ComposeBookConfig,
    modifier: Modifier = Modifier,
) {
    val navState = remember(config) { NavigationState(config.directories) }
    val selectedPath = navState.selectedPath
    val knobScope = remember(selectedPath) { KnobScope() }

    ComposeBookTheme {
        BoxWithConstraints(modifier.fillMaxSize()) {
            if (maxWidth >= DESKTOP_BREAKPOINT.dp) {
                DesktopShellView(
                    knobScope = knobScope,
                    navigationState = navState,
                    addonList = config.addonList,
                )
            } else {
                MobileShellView(
                    knobScope = knobScope,
                    navigationState = navState,
                    addonList = config.addonList,
                )
            }
        }
    }
}

