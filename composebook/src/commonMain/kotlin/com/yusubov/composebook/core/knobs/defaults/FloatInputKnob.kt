package com.yusubov.composebook.core.knobs.defaults

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.extensions.format
import com.yusubov.composebook.core.knobs.Knob

class FloatInputKnob(
    label: String,
    initialValue: Float = 0f,
    description: String = "",
) : Knob<Float>(label, initialValue, description) {

    @Composable
    override fun Content(value: Float, onValueChange: (Float) -> Unit) {
        OutlinedTextField(
            value = value.format(2),
            onValueChange = { it.toFloatOrNull()?.let(onValueChange) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodySmall,
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
        )
    }
}