package com.yusubov.composebook.core.knobs.defaults

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yusubov.composebook.core.knobs.Knob

class IntSliderKnob(
    label: String,
    initialValue: Int = 0,
    val range: IntRange = 0..100,
    val step: Int = 1,
    description: String = "",
) : Knob<Int>(label, initialValue, description) {

    @Composable
    override fun Content(value: Int, onValueChange: (Int) -> Unit) {
        val min = range.first.toFloat()
        val max = range.last.toFloat()
        val steps = ((range.last - range.first) / step) - 1

        Column {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    value.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    "${range.first}..${range.last}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Slider(
                value = value.toFloat().coerceIn(min, max),
                onValueChange = { onValueChange(it.toInt()) },
                valueRange = min..max,
                steps = steps.coerceAtLeast(0),
            )
        }
    }
}