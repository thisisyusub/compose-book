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
import com.yusubov.composebook.core.extensions.format
import com.yusubov.composebook.core.knobs.Knob

class FloatSliderKnob(
    label: String,
    initialValue: Float = 0f,
    val range: ClosedFloatingPointRange<Float> = 0f..1f,
    description: String = "",
) : Knob<Float>(label, initialValue, description) {

    @Composable
    override fun Content(value: Float, onValueChange: (Float) -> Unit) {
        Column {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    value.format(2),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    "${range.start.format(1)}..${range.endInclusive.format(1)}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Slider(
                value = value.coerceIn(range.start, range.endInclusive),
                onValueChange = onValueChange,
                valueRange = range,
            )
        }
    }
}