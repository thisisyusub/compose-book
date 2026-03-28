package com.yusubov.composebook.sample

import androidx.compose.runtime.*
import com.yusubov.composebook.ComposeBook
import com.yusubov.composebook.core.addons.defaults.ThemeOption
import com.yusubov.composebook.dsl.DirectoryBuilder
import com.yusubov.composebook.dsl.composeBookConfig

@Composable
fun ComposeBookDemo() {
    ComposeBook(
        composeBookConfig {
            addons {
                themeAddon(
                    themes = listOf(
                        ThemeOption("Standard Light", standardLight),
                        ThemeOption("Standard Dark", standardDark),
                        ThemeOption("Prime Light", primeLight),
                        ThemeOption("Prime Dark", primeDark),
                    ),
                    builder = { config, content ->
                        CompositionLocalProvider(
                            LocalAppColors provides config.colors,
                            LocalAppTypography provides config.typography,
                            LocalAppSpacing provides config.spacing,
                        ) {
                            content()
                        }
                    },
                )
                viewportAddon()
                textScaleAddon()
            }

            directory("Components") {
                buttonUseCases()
            }
        }
    )
}

fun DirectoryBuilder.buttonUseCases() {
    directory("Button") {
        useCase("Primary") {
            AppButton(label = knob.string("Label", "Click Me"))
        }
        useCase("Outlined") {
            AppButton(label = knob.string("Label", "Cancel"))
        }
    }
}