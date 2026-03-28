package com.yusubov.composebook.core.knobs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun <T : Any> Knob<T>.nullable(): NullableKnob<T> = NullableKnob(this)

class NullableKnob<T : Any>(
    private val inner: Knob<T>,
) : Knob<T?>(
    label = inner.label,
    initialValue = inner.initialValue,
    description = inner.description,
) {
    @Composable
    override fun Content(
        value: T?,
        onValueChange: (T?) -> Unit,
    ) {
        if (value != null) {
            inner.Content(value) { newValue ->
                inner.update(newValue)
                onValueChange(newValue)
            }
        }
    }

    @Composable
    override fun Render() {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        label,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                    )
                    if (!description.isNullOrBlank()) {
                        Text(
                            description,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
                Checkbox(
                    checked = value != null,
                    onCheckedChange = { checked ->
                        if (checked) update(inner.value) else update(null)
                    },
                )
            }

            // Content only when non-null
            if (value != null) {
                Content(value) { update(it) }
            }
        }
    }
}

