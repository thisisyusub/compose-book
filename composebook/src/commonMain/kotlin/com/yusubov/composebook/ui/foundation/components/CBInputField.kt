package com.yusubov.composebook.ui.foundation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@Composable
internal fun CBInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = ComposeBookTheme.typography.body.copy(
            color = ComposeBookTheme.colors.text
        ),
        // Restoring the keyboard logic
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        // Ensure the cursor matches the theme
        cursorBrush = SolidColor(ComposeBookTheme.colors.component),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ComposeBookTheme.sizes.inputHeight)
                    .background(
                        color = ComposeBookTheme.colors.surfaceVariant,
                        shape = RoundedCornerShape(ComposeBookTheme.radii.md)
                    )
                    .border(
                        width = ComposeBookTheme.sizes.borderWidth,
                        color = ComposeBookTheme.colors.border,
                        shape = RoundedCornerShape(ComposeBookTheme.radii.md)
                    )
                    .padding(horizontal = ComposeBookTheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(Modifier.width(ComposeBookTheme.spacing.xs))
                }

                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty() && placeholder != null) {
                        placeholder()
                    }
                    innerTextField()
                }

                if (trailingIcon != null) {
                    Spacer(Modifier.width(ComposeBookTheme.spacing.xs))
                    trailingIcon()
                }
            }
        }
    )
}