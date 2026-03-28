package com.yusubov.composebook.core.addons.defaults

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.addons.Addon

data class ThemeOption<T>(
    val name: String,
    val data: T,
)

class ThemeAddon<T>(
    private val themes: List<ThemeOption<T>>,
    private val builder: @Composable (data: T, content: @Composable () -> Unit) -> Unit,
) : Addon {
    override val name = "Theme"
    var selectedIndex: Int by mutableStateOf(0)

    val selectedTheme: ThemeOption<T>
        get() = themes.getOrElse(selectedIndex) { themes.first() }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun PanelContent() {
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            OutlinedTextField(
                value = selectedTheme.name,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                textStyle = MaterialTheme.typography.bodySmall,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                shape = RoundedCornerShape(8.dp),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                themes.forEachIndexed { index, theme ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                theme.name,
                                style = MaterialTheme.typography.bodySmall,
                            )
                        },
                        onClick = {
                            selectedIndex = index
                            expanded = false
                        },
                    )
                }
            }
        }
    }

    @Composable
    override fun Wrap(content: @Composable () -> Unit) {
        builder(selectedTheme.data, content)
    }
}