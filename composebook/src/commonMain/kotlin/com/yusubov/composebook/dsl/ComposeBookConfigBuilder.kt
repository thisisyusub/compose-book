package com.yusubov.composebook.dsl

import androidx.compose.runtime.Composable
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.core.addons.defaults.DeviceViewport
import com.yusubov.composebook.core.addons.defaults.RecompositionTrackerAddOn
import com.yusubov.composebook.core.addons.defaults.TextScaleAddon
import com.yusubov.composebook.core.addons.defaults.ThemeAddon
import com.yusubov.composebook.core.addons.defaults.ThemeOption
import com.yusubov.composebook.core.addons.defaults.ViewportAddon
import com.yusubov.composebook.core.models.Directory
import com.yusubov.composebook.core.models.UseCase
import com.yusubov.composebook.core.models.UseCaseScope

fun composeBookConfig(block: ComposeBookConfigBuilder.() -> Unit): ComposeBookConfig {
    return ComposeBookConfigBuilder().apply(block).build()
}

@ComposeBookDsl
class ComposeBookConfigBuilder {
    private val directories = mutableListOf<Directory>()
    private val addonsList = mutableListOf<Addon>()

    fun directory(name: String, block: DirectoryBuilder.() -> Unit) {
        directories.add(DirectoryBuilder(name).apply(block).build())
    }

    fun addons(block: AddonsBuilder.() -> Unit) {
        addonsList.addAll(AddonsBuilder().apply(block).build())
    }


    internal fun build() = ComposeBookConfig(directories, addonsList)
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

@ComposeBookDsl
class AddonsBuilder {
    private val addons = mutableListOf<Addon>()

    fun <T> themeAddon(
        themes: List<ThemeOption<T>>,
        builder: @Composable (data: T, content: @Composable () -> Unit) -> Unit,
    ) {
        addons.add(ThemeAddon(themes, builder))
    }

    fun textScaleAddon(initialScale: Float = 1f) {
        addons.add(TextScaleAddon(initialScale))
    }

    fun viewportAddon(viewports: List<DeviceViewport> = DeviceViewport.defaults) {
        addons.add(ViewportAddon(viewports))
    }

    fun recompositionTrackerAddOn() {
        addons.add(RecompositionTrackerAddOn())
    }

    internal fun build(): List<Addon> = addons.toList()
}
