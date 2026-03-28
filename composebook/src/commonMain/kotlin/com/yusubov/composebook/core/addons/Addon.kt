package com.yusubov.composebook.core.addons

import androidx.compose.runtime.Composable

interface Addon {
    val name: String
    @Composable fun PanelContent()
    @Composable fun Wrap(content: @Composable () -> Unit)
}