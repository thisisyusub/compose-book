package com.yusubov.composebook.core.models

import androidx.compose.runtime.Immutable

@Immutable
data class Directory(
    val name: String,
    val useCases: List<UseCase> = emptyList(),
    val subDirectories: List<Directory> = emptyList(),
)