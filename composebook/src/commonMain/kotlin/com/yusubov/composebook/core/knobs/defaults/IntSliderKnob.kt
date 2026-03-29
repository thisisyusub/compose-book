package com.yusubov.composebook.core.knobs.defaults

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.knobs.Knob
import com.yusubov.composebook.ui.foundation.components.CBSlider
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

internal class IntSliderKnob(
    label: String,
    initialValue: Int = 0,
    val range: IntRange = 0..100,
    val step: Int = 1,
    description: String? = null,
) : Knob<Int>(label, initialValue, description) {

    @Composable
    override fun Content(
        value: Int,
        onValueChange: (Int) -> Unit,
    ) {
        val min = range.first.toFloat()
        val max = range.last.toFloat()
        val steps = ((range.last - range.first) / step) - 1

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = value.toString(),
                    style = ComposeBookTheme.typography.label,
                    color = ComposeBookTheme.colors.text,
                )
                Text(
                    text = "${range.first}..${range.last}",
                    style = ComposeBookTheme.typography.caption,
                    color = ComposeBookTheme.colors.textSecondary,
                )
            }
            CBSlider(
                value = value.toFloat().coerceIn(min, max),
                onValueChange = { onValueChange(it.toInt()) },
                valueRange = min..max,
                steps = steps.coerceAtLeast(0),
            )
        }
    }
}