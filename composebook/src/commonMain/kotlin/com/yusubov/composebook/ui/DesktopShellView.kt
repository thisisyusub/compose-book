package com.yusubov.composebook.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.core.navigation.NavigationState

@Composable
internal fun DesktopShellView(
    knobScope: KnobScope,
    navigationState: NavigationState,
) {
    Column(
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    ) {
        Row(Modifier.fillMaxSize()) {
            NavigationMenuView(
                modifier = Modifier.width(280.dp),
                state = navigationState
            )
            VerticalDivider()
        }

        Surface(modifier = Modifier.weight(1f)) {
            Text("Preview will be here...")
        }

        Surface(modifier = Modifier.weight(1f)) {
            Text("Knobs will be here...")
        }
    }
}