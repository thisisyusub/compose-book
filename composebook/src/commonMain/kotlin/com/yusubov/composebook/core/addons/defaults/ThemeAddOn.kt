package com.yusubov.composebook.core.addons.defaults


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.ui.foundation.components.CBDropDown

data class ThemeOption<T>(
    val name: String,
    val data: T,
)

internal class ThemeAddon<T>(
    private val themes: List<ThemeOption<T>>,
    private val builder: @Composable (data: T, content: @Composable () -> Unit) -> Unit,
) : Addon {
    override val name = "Theme"
    var selectedIndex: Int by mutableStateOf(0)

    val selectedTheme: ThemeOption<T>
        get() = themes.getOrElse(selectedIndex) { themes.first() }

    @Composable
    override fun PanelContent() {
        // Extract the names to feed into our custom dropdown
        val themeNames = themes.map { it.name }

        CBDropDown(
            options = themeNames,
            selectedOption = selectedTheme.name,
            onOptionSelected = { selectedName ->
                // Find the index of the newly selected theme
                val newIndex = themes.indexOfFirst { it.name == selectedName }
                if (newIndex != -1) {
                    selectedIndex = newIndex
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    override fun Wrap(content: @Composable () -> Unit) {
        builder(selectedTheme.data, content)
    }
}