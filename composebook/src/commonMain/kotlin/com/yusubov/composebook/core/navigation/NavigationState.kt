package com.yusubov.composebook.core.navigation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.yusubov.composebook.core.models.Category
import com.yusubov.composebook.core.models.Component
import com.yusubov.composebook.core.models.UseCase

@Stable
internal class NavigationState(categories: List<Category>) {
    private val index = buildCatalogIndex(categories)
    val allRows: List<NavRow> = index.rows
    val searchIndex: List<SearchEntry> = index.searchEntries
    val componentCount: Int = index.componentCount
    val useCaseCount: Int = index.useCaseCount

    val expandedNodes = mutableStateMapOf<String, Boolean>()
    var selectedPath: NavigationPath? by mutableStateOf(null)
    var searchQuery: String by mutableStateOf("")

    val searchResults: List<SearchEntry>
        get() {
            val q = searchQuery.lowercase()
            if (q.isBlank()) return emptyList()
            return searchIndex.filter { it.matches(q) }
        }

    val visibleRows: List<NavRow>
        get() {
            val result = mutableListOf<NavRow>()
            var i = 0

            while (i < allRows.size) {
                val row = allRows[i]
                result.add(row)

                if (row is NavRow.CategoryRow && !isExpanded(row.key) ||
                    row is NavRow.ComponentRow && !isExpanded(row.key)
                ) {
                    i = skipChildren(i, row.depth)
                    continue
                }
                i++
            }
            return result
        }

    fun isExpanded(key: String): Boolean = expandedNodes[key] ?: true
    fun toggleExpanded(key: String) {
        expandedNodes[key] = !(expandedNodes[key] ?: true)
    }

    fun select(path: NavigationPath) {
        selectedPath = path
    }

    fun selectFirst() {
        val first = searchIndex.firstOrNull() ?: return
        selectedPath = first.path
    }

    fun getSelectedUseCase(): UseCase? {
        val p = selectedPath ?: return null
        return searchIndex.find { it.path == p }?.useCase
    }

    fun getSelectedComponent(): Component? {
        val p = selectedPath ?: return null
        return searchIndex.find { it.path == p }?.component
    }

    private fun buildCatalogIndex(categories: List<Category>): CatalogIndex {
        val rows = mutableListOf<NavRow>()
        val entries = mutableListOf<SearchEntry>()
        val seenComponents = mutableSetOf<String>()

        fun walk(
            categories: List<Category>,
            depth: Int,
            parentPath: List<String>,
        ) {
            for (category in categories) {
                val categoryPath = parentPath + category.name
                val catPathStr = categoryPath.joinToString("/")
                val categoryKey = "category:$catPathStr"
                val categoryPathLower = catPathStr.lowercase()

                rows.add(NavRow.CategoryRow(categoryKey, depth, category.name))

                for (component in category.components) {
                    val componentKey = "component:$catPathStr/${component.name}"
                    val componentNameLower = component.name.lowercase()

                    seenComponents.add(componentKey)
                    rows.add(NavRow.ComponentRow(componentKey, depth + 1, component))

                    for (useCase in component.useCases) {
                        val path = NavigationPath(
                            categoryPath,
                            component.name,
                            useCase.name,
                        )

                        rows.add(
                            NavRow.UseCaseRow(
                                "useCase:${path.fullPath}",
                                depth + 2,
                                path,
                                component,
                                useCase,
                            )
                        )

                        entries.add(
                            SearchEntry(
                                path = path,
                                component = component,
                                useCase = useCase,
                                componentLower = componentNameLower,
                                useCaseLower = useCase.name.lowercase(),
                                categoryPathLower = categoryPathLower,
                            )
                        )
                    }
                }

                walk(category.subCategories, depth + 1, categoryPath)
            }
        }

        walk(categories, 0, emptyList())

        return CatalogIndex(
            rows = rows,
            searchEntries = entries,
            componentCount = seenComponents.size,
            useCaseCount = entries.size,
        )
    }

    private fun skipChildren(fromIndex: Int, parentDepth: Int): Int {
        var i = fromIndex + 1
        while (i < allRows.size && allRows[i].depth > parentDepth) i++
        return i
    }
}