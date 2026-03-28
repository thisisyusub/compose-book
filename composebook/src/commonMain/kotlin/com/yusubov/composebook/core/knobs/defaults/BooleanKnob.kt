package com.yusubov.composebook.core.knobs.defaults

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.knobs.Knob

class BooleanKnob(
    label: String,
    initialValue: Boolean = false,
    description: String = "",
) : Knob<Boolean>(label, initialValue, description) {

    @Composable
    override fun Content(value: Boolean, onValueChange: (Boolean) -> Unit) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Switch(checked = value, onCheckedChange = onValueChange)
            Text(
                if (value) "true" else "false",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}