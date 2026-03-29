package com.yusubov.composebook.core.models

internal data class NavigationPath(
    val directories: List<String>,
    val useCase: UseCase,
) {
    val fullPath: String get() = (directories + useCase.name).joinToString("/")
}