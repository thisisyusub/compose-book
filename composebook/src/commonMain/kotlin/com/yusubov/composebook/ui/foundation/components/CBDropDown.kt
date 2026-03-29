package com.yusubov.composebook.ui.foundation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBDropDown(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        if (label != null) {
            Text(
                text = label,
                style = ComposeBookTheme.typography.label,
                color = ComposeBookTheme.colors.textSecondary,
                modifier = Modifier.padding(bottom = ComposeBookTheme.spacing.xs)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(ComposeBookTheme.sizes.inputHeight)
                .clip(RoundedCornerShape(ComposeBookTheme.radii.md))
                .background(ComposeBookTheme.colors.surfaceVariant)
                .border(
                    width = ComposeBookTheme.sizes.borderWidth,
                    color = ComposeBookTheme.colors.border,
                    shape = RoundedCornerShape(ComposeBookTheme.radii.md)
                )
                .clickable { expanded = true }
                .padding(horizontal = ComposeBookTheme.spacing.md),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = selectedOption,
                    style = ComposeBookTheme.typography.body,
                    color = ComposeBookTheme.colors.text,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    tint = ComposeBookTheme.colors.textSecondary
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(ComposeBookTheme.colors.surface)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option,
                                style = ComposeBookTheme.typography.body,
                                color = ComposeBookTheme.colors.text
                            )
                        },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}