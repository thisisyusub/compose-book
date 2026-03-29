package com.yusubov.composebook.ui.foundation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val LocalComposeBookTypography = compositionLocalOf { ComposeBookTypography() }

@Immutable
internal data class ComposeBookTypography(
    val heading: TextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
    val body: TextStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
    val bodySmall: TextStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal),
    val label: TextStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
    val caption: TextStyle = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal),
)

