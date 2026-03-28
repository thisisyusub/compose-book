package com.yusubov.composebook.core

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusubov.composebook.core.knobs.KnobScope
import kotlinx.coroutines.launch

private const val DESKTOP_BREAKPOINT = 900

@Composable
fun ComposeBook(
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    val knobScope = remember { KnobScope() }

    BoxWithConstraints(Modifier.fillMaxSize()) {
        if (maxWidth >= DESKTOP_BREAKPOINT.dp) {
            DesktopShell(knobScope)
        } else {
            MobileShell(knobScope)
        }
    }
}

@Composable
private fun DesktopShell(
    knobScope: KnobScope,
) {
    var showNavRail by remember { mutableStateOf(true) }
    var showKnobs by remember { mutableStateOf(true) }

    Column(
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    ) {
        TopBar(
            title = "config.title",
            leadingIcon = {
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = { showNavRail = !showNavRail },
                ) {
                    Icon(
                        if (showNavRail) Icons.AutoMirrored.Filled.MenuOpen
                        else Icons.Default.Menu, "Nav", Modifier.size(18.dp)
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = { showKnobs = !showKnobs },
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Default.Tune,
                        contentDescription = "Knobs",
                        tint = if (showKnobs) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            },
        )
        Row(Modifier.fillMaxSize()) {
            if (showNavRail) {
                Surface(modifier = Modifier.width(280.dp)) {
                    Text("Components menu will be here...")
                }
            }

            Surface(modifier = Modifier.weight(1f)) {
                Text("Preview will be here...")
            }

            if (showKnobs) {
                Surface(modifier = Modifier.weight(1f)) {
                    Text("Knobs will be here...")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MobileShell(
    knobScope: KnobScope,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = { showBottomSheet = false }) {
            Text("Knobs will be here...")
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(Modifier.widthIn(max = 320.dp)) {
                Text("Components menu will be here...")
            }
        },
    ) {
        Column(
            Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        ) {
            TopBar(
                title = "ComposeBook",
                leadingIcon = {
                    IconButton(
                        onClick = { scope.launch { drawerState.open() } },
                    ) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Nav",
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Default.Tune,
                            contentDescription = "Knobs",
                        )
                    }
                },
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            Surface(modifier = Modifier.fillMaxSize()) {
                Text("Preview will be here...")
            }
        }
    }
}

@Composable
private fun TopBar(
    title: String,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIcon()
        Spacer(Modifier.width(8.dp))
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
        Spacer(Modifier.weight(1f))
        trailingIcon()
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
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                count.toString(),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 11.sp
            )
            Text(
                label, style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
            )
        }
    }
}
