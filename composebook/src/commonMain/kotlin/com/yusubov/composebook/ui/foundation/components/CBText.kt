package com.yusubov.composebook.ui.foundation.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = ComposeBookTheme.typography.body,
    color: Color = ComposeBookTheme.colors.text,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {
    // BasicText requires styling to be bundled into the TextStyle object
    val mergedStyle = style.copy(
        color = color,
        fontWeight = fontWeight ?: style.fontWeight,
        textAlign = textAlign ?: style.textAlign
    )

    BasicText(
        text = text,
        modifier = modifier,
        style = mergedStyle,
        overflow = overflow,
        maxLines = maxLines
    )
}