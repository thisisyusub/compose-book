package com.yusubov.composebook.core.navigation

import com.yusubov.composebook.core.models.Component
import com.yusubov.composebook.core.models.UseCase

internal sealed class NavRow {
    abstract val key: String
    abstract val depth: Int

    data class CategoryRow(
        override val key: String,
        override val depth: Int,
        val name: String,
    ) : NavRow()

    data class ComponentRow(
        override val key: String,
        override val depth: Int,
        val component: Component,
    ) : NavRow()

    data class UseCaseRow(
        override val key: String,
        override val depth: Int,
        val path: NavigationPath,
        val component: Component,
        val useCase: UseCase,
    ) : NavRow()
}