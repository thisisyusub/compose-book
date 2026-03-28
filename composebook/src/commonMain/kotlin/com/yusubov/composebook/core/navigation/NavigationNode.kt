package com.yusubov.composebook.core.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.yusubov.composebook.core.models.NavigationPath
import com.yusubov.composebook.core.models.UseCase

sealed class NavigationNode {
    abstract val key: String
}

class DirectoryNode(
    override val key: String,
    val name: String,
    val children: List<NavigationNode>,
) : NavigationNode() {
    var expanded: Boolean by mutableStateOf(false)
}

class UseCaseNode(
    override val key: String,
    val path: NavigationPath,
) : NavigationNode()