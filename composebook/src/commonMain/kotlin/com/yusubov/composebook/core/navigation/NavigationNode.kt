package com.yusubov.composebook.core.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.yusubov.composebook.core.models.NavigationPath
import com.yusubov.composebook.core.models.UseCase

internal sealed class NavigationNode {
    abstract val key: String
}

internal class DirectoryNode(
    override val key: String,
    val name: String,
    val children: List<NavigationNode>,
) : NavigationNode() {
    var expanded: Boolean by mutableStateOf(false)
}

internal class UseCaseNode(
    override val key: String,
    val path: NavigationPath,
) : NavigationNode()