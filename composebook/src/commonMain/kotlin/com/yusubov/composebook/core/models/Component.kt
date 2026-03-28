package com.yusubov.composebook.core.models

import androidx.compose.runtime.Immutable

@Immutable
data class Component(
    val name: String,
    val useCases: List<UseCase> = emptyList(),
)