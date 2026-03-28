package com.yusubov.composebook.core.knobs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateMapOf
import com.yusubov.composebook.core.knobs.defaults.BooleanKnob
import com.yusubov.composebook.core.knobs.defaults.IntSliderKnob
import com.yusubov.composebook.core.knobs.defaults.StringKnob

@Stable
class KnobScope {
    val knobs = mutableStateMapOf<String, Knob<*>>()

    @Composable
    fun string(
        label: String,
        initialValue: String = "",
        description: String? = null,
    ): String = of(StringKnob(label, initialValue, description))

    @Composable
    fun boolean(
        label: String,
        initialValue: Boolean = false,
        description: String? = null,
    ): Boolean = of(BooleanKnob(label, initialValue, description))

    @Composable
    fun intSlider(
        label: String,
        initialValue: Int = 0,
        range: IntRange = 0..100,
    ): Int = of(IntSliderKnob(label, initialValue, range))

    @Composable
    fun <T> of(knob: Knob<T>): T {
        LaunchedEffect(knob.label) {
            if (!knobs.containsKey(knob.label)) {
                knobs[knob.label] = knob
            }
        }

        @Suppress("UNCHECKED_CAST")
        val registered = knobs[knob.label] as? Knob<T>
        return registered?.value ?: knob.initialValue
    }
}