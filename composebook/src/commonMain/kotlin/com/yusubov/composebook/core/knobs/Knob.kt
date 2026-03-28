package com.yusubov.composebook.core.knobs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class Knob<T>(
    val label: String,
    val initialValue: T,
    val description: String = "",
) {
    var value: T by mutableStateOf(initialValue)
        internal set

    fun update(newValue: T) {
        value = newValue
    }

    fun reset() {
        value = initialValue
    }

    @Composable
    abstract fun Content(value: T, onValueChange: (T) -> Unit)

    @Composable
    internal fun Render() {
        Content(value) { update(it) }
    }
}