package com.yusubov.composebook.core.knobs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class NullableKnob<T : Any>(
    private val inner: Knob<T>,
) : Knob<T?>(
    label = inner.label,
    initialValue = inner.initialValue,
    description = inner.description,
) {
    @Composable
    override fun Content(value: T?, onValueChange: (T?) -> Unit) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                if (value == null) "null" else "has value",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Switch(
                checked = value != null,
                onCheckedChange = { checked ->
                    if (checked) onValueChange(inner.value) else onValueChange(null)
                },
            )
        }
        if (value != null) {
            inner.Content(value) { newValue ->
                inner.update(newValue)
                onValueChange(newValue)
            }
        }
    }
}

fun <T : Any> Knob<T>.nullable(): NullableKnob<T> = NullableKnob(this)