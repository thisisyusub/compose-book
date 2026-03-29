package com.yusubov.composebook.core.navigation

import androidx.compose.runtime.Stable
import com.yusubov.composebook.core.models.NavigationPath

@Stable
class SearchEntry(
    val path: NavigationPath,
    private val searchText: String,
) {
    fun matches(query: String): Boolean = searchText.contains(query)
}