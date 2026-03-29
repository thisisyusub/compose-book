package com.yusubov.composebook.core.knobs.defaults

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.yusubov.composebook.core.knobs.Knob
import com.yusubov.composebook.ui.foundation.components.CBInputField

class FloatKnob(
    label: String,
    initialValue: Float = 0f,
    description: String? = null,
) : Knob<Float>(label, initialValue, description) {
    @Composable
    override fun Content(
        value: Float,
        onValueChange: (Float) -> Unit,
    ) {
        var textState by remember(value) {
            mutableStateOf(
                if (value % 1 == 0f) value.toInt().toString()
                else value.toString()
            )
        }

        CBInputField(
            value = textState,
            onValueChange = { newText ->
                textState = newText
                newText.toFloatOrNull()?.let { onValueChange(it) }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
    }
}