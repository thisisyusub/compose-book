package com.yusubov.composebook.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.core.models.UseCaseScope
import com.yusubov.composebook.core.navigation.NavigationState

@Composable
internal fun UseCaseView(
    knobScope: KnobScope,
    navigationState: NavigationState,
    addonList: List<Addon>,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        val useCase = navigationState.getSelectedUseCase()
        if (useCase != null) {
            val scope = remember(knobScope) { UseCaseScope(knob = knobScope) }
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                AddonWrapper(addonList) {
                    useCase.content(scope)
                }
            }
        } else {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "Select a use-case",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}