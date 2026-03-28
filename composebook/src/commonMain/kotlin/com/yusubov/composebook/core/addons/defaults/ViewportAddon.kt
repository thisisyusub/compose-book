package com.yusubov.composebook.core.addons.defaults


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusubov.composebook.core.addons.Addon

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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun PanelContent() {
        var expanded by remember { mutableStateOf(false) }
        val selected = selectedViewport

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            OutlinedTextField(
                value = if (selected.widthDp > 0) "${selected.name} " +
                        "(${selected.widthDp}×${selected.heightDp})"
                else selected.name,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                textStyle = MaterialTheme.typography.bodySmall,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                shape = RoundedCornerShape(8.dp),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                viewports.forEachIndexed { index, viewport ->
                    DropdownMenuItem(
                        text = {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    viewport.name,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                                if (viewport.widthDp > 0) {
                                    Text(
                                        "${viewport.widthDp}×${viewport.heightDp}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 11.sp,
                                    )
                                }
                            }
                        },
                        onClick = {
                            selectedIndex = index
                            expanded = false
                        },
                    )
                }
            }
        }
    }

    @Composable
    override fun Wrap(content: @Composable () -> Unit) {
        val vp = selectedViewport

        if (vp.widthDp == 0 || vp.heightDp == 0) {
            content()
        } else {
            Column(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // Device title
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        vp.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        "${vp.widthDp}×${vp.heightDp}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = 11.sp,
                    )
                }

                // Device frame
                Box(
                    modifier = Modifier
                        .size(width = vp.widthDp.dp, height = vp.heightDp.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                            RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    content()
                }
            }
        }
    }
}