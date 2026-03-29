package com.yusubov.composebook.ui.foundation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        targetValue = if (isFocused) ComposeBookTheme.colors.borderFocused
        else ComposeBookTheme.colors.border,
        label = "BorderColor"
    )
    val borderWidth by animateDpAsState(
        targetValue = if (isFocused) ComposeBookTheme.sizes.borderWidthFocused
        else ComposeBookTheme.sizes.borderWidth,
        label = "BorderWidth"
    )

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.onFocusChanged { isFocused = it.isFocused },
        textStyle = ComposeBookTheme.typography.body.copy(color = ComposeBookTheme.colors.text),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(ComposeBookTheme.colors.component),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ComposeBookTheme.sizes.inputHeight)
                    .background(
                        color = ComposeBookTheme.colors.surfaceVariant,
                        shape = RoundedCornerShape(ComposeBookTheme.radii.md)
                    )
                    .border(
                        width = borderWidth,
                        color = borderColor,
                        shape = RoundedCornerShape(ComposeBookTheme.radii.md)
                    )
                    .padding(horizontal = ComposeBookTheme.spacing.md),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty() && hint.isNotEmpty()) {
                    Text(
                        text = hint,
                        style = ComposeBookTheme.typography.body,
                        color = ComposeBookTheme.colors.textSecondary
                    )
                }
                innerTextField()
            }
        }
    )
}