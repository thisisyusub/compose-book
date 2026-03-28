package com.yusubov.composebook.core.navigation

import com.yusubov.composebook.core.models.Component
import com.yusubov.composebook.core.models.UseCase

class SearchEntry(
    val path: NavigationPath,
    val component: Component,
    val useCase: UseCase,
    private val componentLower: String,
    private val useCaseLower: String,
    private val categoryPathLower: String,
) {
    fun matches(query: String): Boolean =
        componentLower.contains(query) ||
                useCaseLower.contains(query) ||
                categoryPathLower.contains(query)
}
