package com.yusubov.composebook.core.knobs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateMapOf
import com.yusubov.composebook.core.knobs.defaults.BooleanKnob
import com.yusubov.composebook.core.knobs.defaults.DropdownKnob
import com.yusubov.composebook.core.knobs.defaults.FloatKnob
import com.yusubov.composebook.core.knobs.defaults.IntKnob
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
    fun int(
        label: String,
        initialValue: Int = 0,
        description: String? = null,
    ): Int = of(IntKnob(label, initialValue, description))

    @Composable
    fun float(
        label: String,
        initialValue: Float = 0f,
        description: String? = null,
    ): Float = of(FloatKnob(label, initialValue, description))

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
        step: Int = 1,
        description: String = "",
    ): Int = of(
        IntSliderKnob(
            label = label,
            initialValue = initialValue,
            range,
            step = step,
            description = description,
        )
    )

    @Composable
    fun <T> dropdown(
        label: String,
        options: List<T>,
        initialValue: T = options.first(),
        description: String? = null,
        labelMapper: (T) -> String = { it.toString() },
    ): T = of(
        DropdownKnob(
            label = label,
            options = options,
            initialValue = initialValue,
            labelMapper = labelMapper,
            description = description,
        )
    )

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