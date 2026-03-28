package com.yusubov.composebook.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeViewport
import com.yusubov.composebook.ComposeBook
import com.yusubov.composebook.dsl.composeBookConfig
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        ComposeBook(config = buildConfig())
    }
}

private fun buildConfig() = composeBookConfig {
    title = "Acme Design System"

    addons {
        textScaleAddon()
        viewportAddon()
    }

    // ── Components ──
    category("Components") {
        component("Button") {
            useCase("Filled") {
                val label = knob.string("Label", "Click Me")
                val enabled = knob.boolean("Enabled", true)
                val hasIcon = knob.boolean("Leading Icon", false)

                Button(onClick = {}, enabled = enabled) {
                    if (hasIcon) {
                        Icon(
                            Icons.Default.Add,
                            null,
                            Modifier.size(18.dp),
                        )
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(label)
                }
            }
            useCase("Outlined") {
                OutlinedButton(
                    onClick = {},
                    enabled = knob.boolean("Enabled", true),
                ) {
                    Text(knob.string("Label", "Outlined"))
                }
            }
            useCase("FAB") {
                val extended = knob.boolean("Extended", true)
                val label = knob.string("Label", "Create")

                if (extended) {
                    ExtendedFloatingActionButton(onClick = {}) {
                        Icon(Icons.Default.Add, null)
                        Spacer(Modifier.width(8.dp))
                        Text(label)
                    }
                } else {
                    FloatingActionButton(onClick = {}) {
                        Icon(Icons.Default.Add, null)
                    }
                }
            }
        }

        component("TextField") {
            useCase("Outlined") {
                var text by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = text, onValueChange = { text = it },
                    placeholder = { Text(knob.string("Placeholder", "Type here...")) },
                    isError = knob.boolean("Error", false),
                    modifier = Modifier.width(320.dp),
                )
            }
        }

        component("Card") {
            useCase("Elevated") {
                ElevatedCard(Modifier.width(320.dp)) {
                    Column(
                        Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            knob.string("Title", "Card Title"),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            knob.string("Subtitle", "Supporting text"),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if (knob.boolean("Show Action", true)) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                            ) {
                                TextButton(onClick = {}) { Text("Action") }
                            }
                        }
                    }
                }
            }
        }

        component("Progress") {
            useCase("Indicators") {
                val progress = knob.floatSlider("Progress", 0.65f, 0f..1f)
                val indeterminate = knob.boolean("Indeterminate", false)
                Column(
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (indeterminate) {
                        LinearProgressIndicator(Modifier.width(280.dp))
                        CircularProgressIndicator()
                    } else {
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier.width(280.dp)
                        )
                        CircularProgressIndicator(progress = { progress })
                    }
                }
            }
        }

        component("Switch") {
            useCase("Toggle") {
                var checked by remember { mutableStateOf(false) }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Switch(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        enabled = knob.boolean("Enabled", true),
                    )
                    Text(if (checked) "On" else "Off")
                }
            }
        }
    }
}