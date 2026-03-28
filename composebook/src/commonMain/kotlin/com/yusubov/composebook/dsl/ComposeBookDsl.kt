package com.yusubov.composebook.dsl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.yusubov.composebook.core.addons.AddOn
import com.yusubov.composebook.core.addons.textScale.TextScaleAddon
import com.yusubov.composebook.core.addons.viewport.DeviceViewport
import com.yusubov.composebook.core.addons.viewport.ViewportAddon
import com.yusubov.composebook.core.models.Category
import com.yusubov.composebook.core.models.Component
import com.yusubov.composebook.core.models.UseCase
import com.yusubov.composebook.core.models.UseCaseScope

/**
 * The complete configuration for a ComposeBook instance.
 * Created by [composeBookConfig] DSL builder.
 */

@Immutable
data class ComposeBookConfig(
    val title: String = "ComposeBook",
    val categories: List<Category> = emptyList(),
    val addons: List<AddOn> = emptyList(),
)

/**
 * Entry point: creates a [ComposeBookConfig] using the DSL builder.
 *
 * ```kotlin
 * val config = composeBookConfig {
 *     title = "My Design System"
 *     directory("Buttons") { ... }
 * }
 * ```
 */
fun composeBookConfig(block: ComposeBookConfigBuilder.() -> Unit): ComposeBookConfig {
    return ComposeBookConfigBuilder().apply(block).build()
}

// ── Config Builder ──

@ComposeBookDsl
class ComposeBookConfigBuilder {
    /** Title shown in the ComposeBook top bar */
    var title: String = "ComposeBook"

    private val categories = mutableListOf<Category>()
    private val addOns = mutableListOf<AddOn>()

    /** Add a category (folder) to the navigation tree */
    fun category(name: String, block: CategoryBuilder.() -> Unit) {
        categories.add(CategoryBuilder(name).apply(block).build())
    }

    /** Configure addons (theme, text scale, padding, viewport, etc.) */
    fun addons(block: AddonsBuilder.() -> Unit) {
        addOns.addAll(AddonsBuilder().apply(block).build())
    }

    internal fun build() = ComposeBookConfig(title, categories, addOns)
}

// ── Category Builder ──

@ComposeBookDsl
class CategoryBuilder(private val name: String) {
    private val components = mutableListOf<Component>()
    private val subcategories = mutableListOf<Category>()

    /** Add a component to this category */
    fun component(name: String, block: ComponentBuilder.() -> Unit) {
        components.add(ComponentBuilder(name).apply(block).build())
    }

    fun category(name: String, block: CategoryBuilder.() -> Unit) {
        subcategories.add(CategoryBuilder(name).apply(block).build())
    }

    internal fun build() = Category(name, components, subcategories)
}

// ── Component Builder ──

@ComposeBookDsl
class ComponentBuilder(private val name: String) {
    private val useCases = mutableListOf<UseCase>()

    /** Add a use-case (a specific state/variant of this component) */
    fun useCase(name: String, content: @Composable UseCaseScope.() -> Unit) {
        useCases.add(UseCase(name, content))
    }

    internal fun build() = Component(name, useCases)
}

// ── Addons Builder ──

@ComposeBookDsl
class AddonsBuilder {
    private val addons = mutableListOf<AddOn>()


    /** Add a text scaling addon (for accessibility testing) */
    fun textScaleAddon(initialScale: Float = 1f) {
        addons.add(TextScaleAddon(initialScale))
    }


    /** Add a viewport simulation addon */
    fun viewportAddon(viewports: List<DeviceViewport> = DeviceViewport.defaults) {
        addons.add(ViewportAddon(viewports))
    }


    /** Add any custom addon implementation */
    fun addon(addon: AddOn) {
        addons.add(addon)
    }

    internal fun build(): List<AddOn> = addons.toList()
}