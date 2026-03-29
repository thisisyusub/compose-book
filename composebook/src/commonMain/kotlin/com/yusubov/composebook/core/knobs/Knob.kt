package com.yusubov.composebook.core.knobs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.yusubov.composebook.ui.foundation.components.CBText
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Stable
abstract class Knob<T>(
    val label: String,
    val initialValue: T,
    val description: String? = null,
) {
    var value: T by mutableStateOf(initialValue)
        internal set

    fun update(newValue: T) {
        value = newValue
    }

    @Composable
    abstract fun Content(value: T, onValueChange: (T) -> Unit)

    @Composable
    open fun Render() {
        Column(verticalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.xs)) {
            CBText(
                text = label,
                style = ComposeBookTheme.typography.label,
                color = ComposeBookTheme.colors.text,
            )
            if (!description.isNullOrBlank()) {
                CBText(
                    text = description,
                    style = ComposeBookTheme.typography.bodySmall,
                    color = ComposeBookTheme.colors.textSecondary,
                )
            }
            Content(value) { update(it) }
        }
    }
}