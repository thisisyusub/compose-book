package com.yusubov.composebook.core.knobs.defaults

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.knobs.Knob

class IntInputKnob(
    label: String,
    initialValue: Int = 0,
    description: String = "",
) : Knob<Int>(label, initialValue, description) {

    @Composable
    override fun Content(value: Int, onValueChange: (Int) -> Unit) {
        OutlinedTextField(
            value = value.toString(),
            onValueChange = { it.toIntOrNull()?.let(onValueChange) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodySmall,
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
        )
    }
}