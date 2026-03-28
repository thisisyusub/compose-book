package com.yusubov.composebook.sample

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.ComposeBook
import com.yusubov.composebook.dsl.composeBookConfig

@Composable
fun ComposeBookDemo() {
    ComposeBook(
        composeBookConfig {
            directory("Foundation") {
                directory("Colors") {
                    directory("Primary") {}
                    directory("Secondary") {}
                    directory("Tertiary") {}
                    directory("Error") {}
                    directory("Neutral") {}
                }
                directory("Typography") {
                    directory("Display") {}
                    directory("Headline") {}
                    directory("Title") {}
                    directory("Body") {}
                    directory("Label") {}
                }
                directory("Spacing") {
                    directory("4dp") {}
                    directory("8dp") {}
                    directory("12dp") {}
                    directory("16dp") {}
                    directory("24dp") {}
                    directory("32dp") {}
                    directory("48dp") {}
                }
                directory("Shapes") {
                    directory("Small") {}
                    directory("Medium") {}
                    directory("Large") {}
                    directory("Extra Large") {}
                }
                directory("Icons") {
                    directory("Filled") {}
                    directory("Outlined") {}
                    directory("Rounded") {}
                }
            }
            directory("Components") {
                directory("Button") {
                    directory("Filled") {}
                    directory("Outlined") {}
                    directory("Text") {}
                    directory("Tonal") {}
                    directory("Elevated") {}
                    directory("Icon") {}
                }
                directory("Input") {
                    directory("TextField") {
                        directory("Outlined") {}
                        directory("Filled") {}
                    }
                    directory("Dropdown") {}
                    directory("Checkbox") {}
                    directory("RadioButton") {}
                    directory("Switch") {}
                    directory("Slider") {}
                    directory("DatePicker") {}
                    directory("TimePicker") {}
                }
                directory("Card") {
                    directory("Elevated") {}
                    directory("Filled") {}
                    directory("Outlined") {}
                }
                directory("Progress") {
                    directory("Linear") {}
                    directory("Circular") {}
                }
                directory("Chip") {
                    directory("Assist") {}
                    directory("Filter") {}
                    directory("Input") {}
                    directory("Suggestion") {}
                }
                directory("Badge") {}
                directory("Divider") {}
                directory("Tooltip") {}
            }
            directory("Feedback") {
                directory("Snackbar") {}
                directory("Dialog") {
                    directory("Alert") {}
                    directory("Confirmation") {}
                    directory("Full Screen") {}
                }
                directory("Toast") {}
                directory("Banner") {}
                directory("BottomSheet") {}
            }
            directory("Navigation") {
                directory("TopAppBar") {
                    directory("Small") {}
                    directory("Medium") {}
                    directory("Large") {}
                }
                directory("BottomNavigation") {}
                directory("NavigationRail") {}
                directory("NavigationDrawer") {}
                directory("Tabs") {
                    directory("Primary") {}
                    directory("Secondary") {}
                }
                directory("Breadcrumbs") {}
            }
            directory("Layout") {
                directory("Scaffold") {}
                directory("List") {
                    directory("Simple") {}
                    directory("With Icon") {}
                    directory("With Action") {}
                }
                directory("Grid") {}
                directory("Carousel") {}
            }
            directory("Patterns") {
                directory("Empty State") {}
                directory("Loading State") {}
                directory("Error State") {}
                directory("Onboarding") {}
                directory("Search") {}
                directory("Settings") {}
            }
        },
        darkTheme = true,
    )
}