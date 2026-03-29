package com.yusubov.composebook.core.knobs.defaults

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.yusubov.composebook.core.knobs.Knob
import com.yusubov.composebook.ui.foundation.components.CBToggle
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

internal class BooleanKnob(
    label: String,
    initialValue: Boolean = false,
    description: String? = null,
) : Knob<Boolean>(label, initialValue, description) {
    @Composable
    override fun Content(
        value: Boolean,
        onValueChange: (Boolean) -> Unit,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.sm),
        ) {
            CBToggle(checked = value, onCheckedChange = onValueChange)
            Text(
                text = if (value) "true" else "false",
                style = ComposeBookTheme.typography.bodySmall,
                color = ComposeBookTheme.colors.textSecondary,
            )
        }
    }
}