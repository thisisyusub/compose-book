package com.yusubov.composebook.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.core.knobs.KnobScope
import com.yusubov.composebook.core.models.UseCaseScope
import com.yusubov.composebook.core.navigation.NavigationState
import com.yusubov.composebook.ui.foundation.components.CBText
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun UseCaseView(
    knobScope: KnobScope,
    navigationState: NavigationState,
    addonList: List<Addon>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ComposeBookTheme.colors.background)
    ) {
        val useCase = navigationState.getSelectedUseCase()

        if (useCase != null) {
            val scope = remember(knobScope) { UseCaseScope(knob = knobScope) }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                AddonWrapper(addonList) {
                    useCase.content(scope)
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CBText(
                    text = "Select a use-case",
                    style = ComposeBookTheme.typography.body,
                    color = ComposeBookTheme.colors.textSecondary,
                )
            }
        }
    }
}