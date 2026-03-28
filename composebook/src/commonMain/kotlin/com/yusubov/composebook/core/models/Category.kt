package com.yusubov.composebook.core.models

import androidx.compose.runtime.Immutable

@Immutable
data class Category(
    val name: String,
    val components: List<Component> = emptyList(),
    val subCategories: List<Category> = emptyList(),
)