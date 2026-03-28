package com.yusubov.composebook.core.models

import androidx.compose.runtime.saveable.listSaver

data class NavigationPath(
    val directories: List<String>,
    val useCase: UseCase,
) {
    val fullPath: String get() = (directories + useCase.name).joinToString("/")
}