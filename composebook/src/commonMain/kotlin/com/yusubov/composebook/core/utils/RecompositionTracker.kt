package com.yusubov.composebook.core.utils

import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.yusubov.composebook.core.addons.defaults.LocalRecompositionTrackingEnabled
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme
import kotlin.math.max
import kotlin.math.roundToInt

@Stable
internal fun Modifier.recompositionTracker(): Modifier = composed {
    val isEnabled = LocalRecompositionTrackingEnabled.current
    if (!isEnabled) return@composed this

    // 2. Track recompositions in this specific scope
    var recompositions by remember { mutableIntStateOf(0) }

    SideEffect {
        recompositions++
    }

    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current

    // 1. Define fixed UI styles
    val fixedFontSize = with(density) { 10.dp.toSp() }
    val textStyle = ComposeBookTheme.typography.bodySmall.copy(
        color = Color.Red,
        fontSize = fixedFontSize,
    )
    val strokeWidthPx = with(density) { ComposeBookTheme.sizes.borderWidth.toPx() }
    val gapPx = with(density) { 2.dp.toPx() }

    drawWithCache {
        val textResult = textMeasurer.measure("Recompositions: $recompositions", textStyle)
        val textHeight = textResult.size.height.toFloat()

        onDrawWithContent {
            // 1. Draw the text at the top left
            drawText(
                textLayoutResult = textResult,
                topLeft = Offset(strokeWidthPx + gapPx, gapPx / 2f)
            )

            // 2. Draw the red boundary box
            // We start it exactly below the text and wrap it down
            drawRect(
                color = Color.Red,
                topLeft = Offset(strokeWidthPx / 2f, textHeight + gapPx),
                size = Size(
                    width = size.width - strokeWidthPx,
                    height = size.height - textHeight - gapPx - (gapPx / 2f)
                ),
                style = Stroke(width = strokeWidthPx)
            )

            // 3. Draw the actual component (which was positioned by the layout block above)
            drawContent()
        }
    }.layout { measurable, constraints ->
        // 1. Measure the text dynamically
        val textResult = textMeasurer.measure("Recompositions: $recompositions", textStyle)
        val textHeight = textResult.size.height

        // 2. Measure the target component
        val placeable = measurable.measure(constraints)

        val borderWidthInt = strokeWidthPx.roundToInt()
        val gapInt = gapPx.roundToInt()

        // 3. calculate final size for content
        val finalWidth =
            max(placeable.width, textResult.size.width) + (borderWidthInt * 2) + (gapInt * 2)
        val finalHeight =
            textResult.size.height + placeable.height + (borderWidthInt * 2) + (gapInt * 3)

        // 4. Report the exact required size to Jetpack Compose to prevent clipping
        layout(finalWidth, finalHeight) {
            // Place the component inside the box, pushing it down below the text
            placeable.placeRelative(
                x = borderWidthInt + gapInt,
                y = textHeight + borderWidthInt + (gapInt * 2)
            )
        }
    }
}