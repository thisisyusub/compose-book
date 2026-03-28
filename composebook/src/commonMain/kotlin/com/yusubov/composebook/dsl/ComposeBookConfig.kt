package com.yusubov.composebook.dsl

import androidx.compose.runtime.Immutable
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.core.models.Directory

@Immutable
data class ComposeBookConfig(
    val directories: List<Directory> = emptyList(),
    val addonList: List<Addon> = emptyList(),
)