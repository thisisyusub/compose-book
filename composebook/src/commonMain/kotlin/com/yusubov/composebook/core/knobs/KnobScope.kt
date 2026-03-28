package com.yusubov.composebook.core.knobs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateMapOf
import com.yusubov.composebook.core.knobs.defaults.BooleanKnob
import com.yusubov.composebook.core.knobs.defaults.DropdownKnob
import com.yusubov.composebook.core.knobs.defaults.FloatInputKnob
import com.yusubov.composebook.core.knobs.defaults.FloatSliderKnob
import com.yusubov.composebook.core.knobs.defaults.IntInputKnob
import com.yusubov.composebook.core.knobs.defaults.IntSliderKnob
import com.yusubov.composebook.core.knobs.defaults.StringKnob

@Stable
class KnobScope {
    val knobs = mutableStateMapOf<String, Knob<*>>()

    @Composable
    fun string(
        label: String,
        initialValue: String = "",
        description: String = "",
    ): String =
        of(StringKnob(label, initialValue, description))

    @Composable
    fun boolean(
        label: String,
        initialValue: Boolean = false,
        description: String = "",
    ): Boolean =
        of(BooleanKnob(label, initialValue, description))

    @Composable
    fun intInput(
        label: String,
        initialValue: Int = 0,
        description: String = "",
    ): Int =
        of(IntInputKnob(label, initialValue, description))

    @Composable
    fun intSlider(
        label: String,
        initialValue: Int = 0,
        range: IntRange = 0..100,
        step: Int = 1,
        description: String = "",
    ): Int =
        of(IntSliderKnob(label, initialValue, range, step, description))

    @Composable
    fun floatInput(
        label: String,
        initialValue: Float = 0f,
        description: String = "",
    ): Float =
        of(FloatInputKnob(label, initialValue, description))

    @Composable
    fun floatSlider(
        label: String,
        initialValue: Float = 0f,
        range: ClosedFloatingPointRange<Float> = 0f..1f,
        description: String = "",
    ): Float =
        of(FloatSliderKnob(label, initialValue, range, description))

    @Composable
    fun <T> dropdown(
        label: String,
        options: List<T>,
        initialValue: T = options.first(),
        labelMapper: (T) -> String = { it.toString() },
        description: String = "",
    ): T =
        of(DropdownKnob(label, initialValue, options, labelMapper, description))


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

    fun clear() { knobs.clear() }
}