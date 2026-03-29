package com.yusubov.composebook.core.knobs.defaults

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.knobs.Knob
import com.yusubov.composebook.ui.foundation.components.CBDropDown

internal class DropdownKnob<T>(
    label: String,
    val options: List<T>,
    initialValue: T = options.first(),
    val labelMapper: (T) -> String = { it.toString() },
    description: String? = null,
) : Knob<T>(label, initialValue, description) {
    @Composable
    override fun Content(
        value: T,
        onValueChange: (T) -> Unit,
    ) {
        val stringOptions = options.map { labelMapper(it) }
        val selectedString = labelMapper(value)

        CBDropDown(
            options = stringOptions,
            selectedOption = selectedString,
            onOptionSelected = { selected ->
                val selectedItem = options.firstOrNull { labelMapper(it) == selected }
                if (selectedItem != null) {
                    onValueChange(selectedItem)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}