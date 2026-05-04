package com.yusubov.composebook.core.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.core.utils.recompositionTracker

class UseCaseScope(val knob: KnobScope) {
    // Provide a catalog-aware modifier that developers can attach to their root component
    val modifier: Modifier
        @Composable
        get() = Modifier.recompositionTracker()
}