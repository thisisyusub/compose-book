package com.yusubov.composebook.core.navigation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.yusubov.composebook.core.models.Directory
import com.yusubov.composebook.core.models.NavigationPath
import com.yusubov.composebook.core.models.UseCase

@Stable
internal class NavigationState(directories: List<Directory>) {
    val rootNodes: List<NavigationNode>
    val searchIndex: List<SearchEntry>
    var selectedPath: NavigationPath? by mutableStateOf(null)
    var searchQuery: String by mutableStateOf("")

    init {
        val entries = mutableListOf<SearchEntry>()

        fun build(dirs: List<Directory>, parentPath: List<String>): List<NavigationNode> {
            return dirs.flatMap { dir ->
                val dirPath = parentPath + dir.name
                val dirPathStr = dirPath.joinToString("/")

                val children = mutableListOf<NavigationNode>()

                for (uc in dir.useCases) {
                    val path = NavigationPath(dirPath, uc)
                    children.add(UseCaseNode("uc:${path.fullPath}", path))
                    entries.add(
                        SearchEntry(
                            path,
                            "${dirPathStr}/${uc.name}".lowercase(),
                        ),
                    )
                }

                children.addAll(build(dir.subDirectories, dirPath))
                listOf(DirectoryNode("dir:$dirPathStr", dir.name, children))
            }
        }

        rootNodes = build(directories, emptyList())
        searchIndex = entries
    }


    val searchResults: List<SearchEntry>
        get() {
            val q = searchQuery.lowercase()
            if (q.isBlank()) return emptyList()
            return searchIndex.filter { it.matches(q) }
        }

    fun toggleExpanded(dirNode: DirectoryNode) {
        dirNode.expanded = !dirNode.expanded
    }

    fun select(path: NavigationPath) {
        selectedPath = path
    }

    fun selectFirst() {
        searchIndex.firstOrNull()?.let { selectedPath = it.path }
    }

    fun getSelectedUseCase(): UseCase? =
        selectedPath?.let { p -> searchIndex.find { it.path == p }?.path?.useCase }
}