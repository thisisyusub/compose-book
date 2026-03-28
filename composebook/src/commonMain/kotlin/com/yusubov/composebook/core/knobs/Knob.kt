package com.yusubov.composebook.core.knobs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Stable
abstract class Knob<T>(
    val label: String,
    val initialValue: T,
    val description: String? = null,
) {
    var value: T by mutableStateOf(initialValue)
        internal set

    fun update(newValue: T) {
        value = newValue
    }

    @Composable
    abstract fun Content(value: T, onValueChange: (T) -> Unit)

    @Composable
    open fun Render() {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium,
            )
            if (!description.isNullOrBlank()) {
                Text(
                    description,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Content(value) { update(it) }
        }
    }
}