package com.yusubov.composebook.core.navigation

data class NavigationPath(
    val categories: List<String>,
    val component: String,
    val useCase: String,
) {
    val fullPath: String get() = (categories + component + useCase).joinToString("/")
}