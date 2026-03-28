package com.yusubov.composebook.core.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
data class UseCase(
    val name: String,
    val content: @Composable (UseCaseScope) -> Unit,
)