package com.yusubov.composebook

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.core.navigation.NavigationState
import com.yusubov.composebook.dsl.ComposeBookConfig
import com.yusubov.composebook.theme.ComposeBookTheme
import com.yusubov.composebook.ui.panels.knob.KnobPanel
import com.yusubov.composebook.ui.panels.navigation.NavigationPanel
import com.yusubov.composebook.ui.panels.preview.PreviewPanel

@Composable
fun ComposeBook(
    config: ComposeBookConfig,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    val navState = remember(config) {
        NavigationState(config.categories).also { it.selectFirst() }
    }
    val knobScope = remember { KnobScope() }
    val selectedPath = navState.selectedPath

    LaunchedEffect(selectedPath) { knobScope.clear() }

    ComposeBookTheme(darkTheme = darkTheme) {
        Shell(config, navState, knobScope)
    }
}

@Composable
private fun Shell(
    config: ComposeBookConfig,
    navState: NavigationState,
    knobScope: KnobScope,
) {
    var showNav by remember { mutableStateOf(true) }
    var showKnobs by remember { mutableStateOf(true) }

    Column(
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { showNav = !showNav },
                modifier = Modifier.size(32.dp),
            ) {
                Icon(
                    if (showNav) Icons.AutoMirrored.Filled.MenuOpen
                    else Icons.Default.Menu,
                    "Toggle nav",
                    Modifier.size(18.dp),
                )
            }
            Spacer(Modifier.width(8.dp))
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        "C",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(Modifier.width(8.dp))
            Text(
                config.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.width(16.dp))
            StatBadge("Components", navState.componentCount)
            Spacer(Modifier.width(8.dp))
            StatBadge("Use-cases", navState.useCaseCount)
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { showKnobs = !showKnobs }, modifier = Modifier.size(32.dp)) {
                Icon(
                    Icons.Default.Tune,
                    "Toggle knobs",
                    Modifier.size(18.dp),
                    tint = if (showKnobs) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

        Row(Modifier.fillMaxSize()) {
            if (showNav) {
                NavigationPanel(navState, Modifier.width(280.dp))
                VerticalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            }
            PreviewPanel(
                navState.getSelectedComponent(),
                navState.getSelectedUseCase(),
                knobScope,
                config.addons,
                Modifier.weight(1f)
            )
            if (showKnobs) {
                VerticalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                KnobPanel(knobScope, config.addons, Modifier.width(300.dp))
            }
        }
    }

}

@Composable
private fun StatBadge(label: String, count: Int) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Row(
            Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                count.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp
            )
        }
    }
}