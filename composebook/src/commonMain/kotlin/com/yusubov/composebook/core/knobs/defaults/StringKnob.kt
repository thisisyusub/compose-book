package com.yusubov.composebook.core.knobs.defaults

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.knobs.Knob
import com.yusubov.composebook.ui.foundation.components.CBInputField

class StringKnob(
    label: String,
    initialValue: String = "",
    description: String? = null,
) : Knob<String>(label, initialValue, description) {
    @Composable
    override fun Content(
        value: String,
        onValueChange: (String) -> Unit,
    ) {
        CBInputField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}