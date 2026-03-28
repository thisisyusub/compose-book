package com.yusubov.composebook.dsl

import androidx.compose.runtime.Immutable
import com.yusubov.composebook.core.models.Directory

@Immutable
data class ComposeBookConfig(
    val title: String = "ComposeBook",
    val directories: List<Directory> = emptyList(),
    // val addons: List<Addon> = emptyList(),
)