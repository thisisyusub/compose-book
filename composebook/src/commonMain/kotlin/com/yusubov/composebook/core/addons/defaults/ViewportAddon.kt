package com.yusubov.composebook.core.addons.defaults


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.addons.Addon
import com.yusubov.composebook.ui.foundation.components.CBDropDown
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

data class DeviceViewport(
    val name: String,
    val widthDp: Int,
    val heightDp: Int,
) {
    companion object {
        val None = DeviceViewport("No Frame", 0, 0)
        val Pixel7 = DeviceViewport("Pixel 7", 412, 915)
        val Pixel7Pro = DeviceViewport("Pixel 7 Pro", 412, 892)
        val Pixel9 = DeviceViewport("Pixel 9", 412, 922)
        val Pixel9Pro = DeviceViewport("Pixel 9 Pro", 448, 998)
        val Pixel9ProFold = DeviceViewport("Pixel 9 Pro Fold", 600, 839)
        val iPhone15 = DeviceViewport("iPhone 15", 393, 852)
        val iPhone15ProMax = DeviceViewport("iPhone 15 Pro Max", 430, 932)
        val iPhone16 = DeviceViewport("iPhone 16", 393, 852)
        val iPhone16ProMax = DeviceViewport("iPhone 16 Pro Max", 440, 956)
        val iPadMini = DeviceViewport("iPad Mini", 744, 1133)
        val iPadPro11 = DeviceViewport("iPad Pro 11\"", 834, 1194)
        val GalaxyS24 = DeviceViewport("Galaxy S24", 360, 780)
        val GalaxyS24Ultra = DeviceViewport("Galaxy S24 Ultra", 412, 915)
        val Desktop720 = DeviceViewport("Desktop 720p", 1280, 720)
        val Desktop1080 = DeviceViewport("Desktop 1080p", 1920, 1080)

        val defaults = listOf(
            None, Pixel9, Pixel9Pro, Pixel9ProFold,
            iPhone16, iPhone16ProMax,
            GalaxyS24, GalaxyS24Ultra,
            iPadMini, Desktop1080,
        )
    }
}

class ViewportAddon(
    private val viewports: List<DeviceViewport> = DeviceViewport.defaults,
) : Addon {
    override val name = "Viewport"
    var selectedIndex: Int by mutableStateOf(0)

    val selectedViewport: DeviceViewport
        get() = viewports.getOrElse(selectedIndex) { DeviceViewport.None }

    @Composable
    override fun PanelContent() {
        val selected = selectedViewport

        // Format the options for our CBDropDown component
        val options = viewports.map { vp ->
            if (vp.widthDp > 0) "${vp.name} (${vp.widthDp}×${vp.heightDp})" else vp.name
        }

        val selectedOptionString = if (selected.widthDp > 0) {
            "${selected.name} (${selected.widthDp}×${selected.heightDp})"
        } else {
            selected.name
        }

        CBDropDown(
            options = options,
            selectedOption = selectedOptionString,
            onOptionSelected = { selectedString ->
                val index = options.indexOf(selectedString)
                if (index != -1) {
                    selectedIndex = index
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    override fun Wrap(content: @Composable () -> Unit) {
        val vp = selectedViewport

        if (vp.widthDp == 0 || vp.heightDp == 0) {
            content()
        } else {
            Column(
                modifier = Modifier.padding(all = ComposeBookTheme.spacing.sm),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.sm),
            ) {
                // Device title
                Row(
                    horizontalArrangement = Arrangement.spacedBy(ComposeBookTheme.spacing.sm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = vp.name,
                        style = ComposeBookTheme.typography.label,
                        color = ComposeBookTheme.colors.text,
                    )
                    Text(
                        text = "${vp.widthDp}×${vp.heightDp}",
                        style = ComposeBookTheme.typography.caption,
                        color = ComposeBookTheme.colors.textSecondary,
                    )
                }

                // Device frame
                Box(
                    modifier = Modifier
                        .size(width = vp.widthDp.dp, height = vp.heightDp.dp)
                        .clip(RoundedCornerShape(ComposeBookTheme.radii.xl))
                        .border(
                            width = ComposeBookTheme.sizes.borderWidthFocused,
                            color = ComposeBookTheme.colors.borderFocused,
                            shape = RoundedCornerShape(ComposeBookTheme.radii.xl)
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    content()
                }
            }
        }
    }
}