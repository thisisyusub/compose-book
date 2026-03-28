package com.yusubov.composebook.ui.components

import androidx.compose.runtime.Composable
import com.yusubov.composebook.core.addons.Addon

@Composable
internal fun AddonWrapper(
    addons: List<Addon>,
    index: Int = 0,
    content: @Composable () -> Unit,
) {
    if (index >= addons.size) {
        content()
    } else {
        addons[index].Wrap {
            AddonWrapper(addons, index + 1, content)
        }
    }
}