package com.yusubov.composebook.dsl

import androidx.compose.runtime.Composable
import com.yusubov.composebook.core.models.Directory
import com.yusubov.composebook.core.models.UseCase
import com.yusubov.composebook.core.models.UseCaseScope

fun composeBookConfig(block: ComposeBookConfigBuilder.() -> Unit): ComposeBookConfig {
    return ComposeBookConfigBuilder().apply(block).build()
}

@ComposeBookDsl
class ComposeBookConfigBuilder {
    var title: String = "ComposeBook"
    private val directories = mutableListOf<Directory>()

    fun directory(name: String, block: DirectoryBuilder.() -> Unit) {
        directories.add(DirectoryBuilder(name).apply(block).build())
    }

    internal fun build() = ComposeBookConfig(title, directories)
}

@ComposeBookDsl
class DirectoryBuilder(private val name: String) {
    private val useCases = mutableListOf<UseCase>()
    private val subdirectories = mutableListOf<Directory>()

    fun useCase(name: String, content: @Composable UseCaseScope.() -> Unit) {
        useCases.add(UseCase(name, content))
    }

    fun directory(name: String, block: DirectoryBuilder.() -> Unit) {
        subdirectories.add(DirectoryBuilder(name).apply(block).build())
    }

    internal fun build() = Directory(name, useCases, subdirectories)
}