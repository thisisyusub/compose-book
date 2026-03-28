package com.yusubov.composebook.core.addons.viewport

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.addons.AddOn

class ViewportAddon(
    private val viewports: List<DeviceViewport> = DeviceViewport.defaults,
) : AddOn {
    override val name = "Viewport"
    var selectedIndex: Int by mutableStateOf(0)
    val selectedViewport: DeviceViewport get() = viewports.getOrElse(selectedIndex) { DeviceViewport.None }

    @Composable
    override fun PanelContent() {
        ViewportAddonPanel(viewports, selectedIndex) { selectedIndex = it }
    }

    @Composable
    override fun Wrap(content: @Composable () -> Unit) {
        val vp = selectedViewport
        if (vp.width == 0 || vp.height == 0) content()

        else Box(
            modifier = Modifier
                .size(width = vp.width.dp, height = vp.height.dp)
                .border(
                    Dp(1f),
                    Color.Gray.copy(alpha = 0.3f),
                    RoundedCornerShape(8.dp),
                )
                .clip(RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center,
        ) { content() }
    }
}