package com.yusubov.composebook.core.addons.textScale

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
internal fun TextScaleAddonPanel(
    scale: Float,
    onScaleChanged: (Float) -> Unit,
) {
    val scales = listOf(0.75f, 1f, 1.25f, 1.5f, 2f)

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            BasicText(
                "Scale: ${(scale * 100).roundToInt()}%",
            )
            BasicText("$scale x")
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            scales.forEach { preset ->
                val s = (scale * 100).roundToInt() == (preset * 100).roundToInt()

                SuggestionChip(
                    onClick = { onScaleChanged(preset) },
                    label = { Text("${(preset * 100).roundToInt()}%", fontSize = 11.sp) },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor =
                            if (s) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surfaceVariant
                    ),
                    modifier = Modifier.height(28.dp)
                )
            }
        }
    }
}