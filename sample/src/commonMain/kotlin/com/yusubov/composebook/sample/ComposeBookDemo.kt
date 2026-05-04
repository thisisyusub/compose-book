package com.yusubov.composebook.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(config.colors.background),
                                contentAlignment = Alignment.Center,
                            ) {
                                content()
                            }
                        }
                    },
                )
                viewportAddon()
                textScaleAddon()
                recompositionTrackerAddOn()
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
            val stringKnob = knob.string("Label", "Hello")
            val booleanKnob = knob.boolean("Enabled", true)
            val intSliderKnob = knob.intSlider("Size", initialValue = 16, range = 8..32)
            val intKnob = knob.int("Int", 123)
            val floatKnob = knob.float("Float", 123.456f)
            val dropdownKnob = knob.dropdown("Dropdown", listOf("Option 1", "Option 2", "Option 3"))

            AppButton(
                modifier = modifier,
                label = knob.string("Label", "Click Me")
            )
        }
        useCase("Outlined") {
            AppButton(label = knob.string("Label", "Cancel"))
        }
    }
}