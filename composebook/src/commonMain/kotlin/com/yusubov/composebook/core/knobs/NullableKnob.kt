package com.yusubov.composebook.core.knobs

import com.yusubov.composebook.ui.foundation.components.CBCheckBox


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

fun <T : Any> Knob<T>.nullable(): NullableKnob<T> = NullableKnob(this)

class NullableKnob<T : Any>(
    private val inner: Knob<T>,
) : Knob<T?>(
    label = inner.label,
    initialValue = inner.initialValue,
    description = inner.description,
) {
    @Composable
    override fun Content(
        value: T?,
        onValueChange: (T?) -> Unit,
    ) {
        if (value != null) {
            inner.Content(value) { newValue ->
                inner.update(newValue)
                onValueChange(newValue)
            }
        }
    }

    @Composable
    override fun Render() {
        Column(verticalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.xs)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = label,
                        style = ComposeBookTheme.typography.label,
                        color = ComposeBookTheme.colors.text,
                    )
                    if (!description.isNullOrBlank()) {
                        Text(
                            text = description,
                            style = ComposeBookTheme.typography.caption,
                            color = ComposeBookTheme.colors.textSecondary,
                        )
                    }
                }
                CBCheckBox(
                    checked = value != null,
                    onCheckedChange = { checked ->
                        if (checked) update(inner.value)
                        else update(null)
                    },
                )
            }

            // Content only when non-null
            if (value != null) {
                Content(value) { update(it) }
            }
        }
    }
}