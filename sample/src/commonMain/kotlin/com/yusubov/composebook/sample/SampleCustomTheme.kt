package com.yusubov.composebook.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
// Your custom design system (no Material)
// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

data class AppColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val text: Color,
    val textSecondary: Color,
)

data class AppTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
)

data class AppSpacing(
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
)

data class AppThemeConfig(
    val colors: AppColors,
    val typography: AppTypography,
    val spacing: AppSpacing,
)

// CompositionLocals — your components read from these
val LocalAppColors = compositionLocalOf<AppColors> { error("No AppColors provided") }
val LocalAppTypography = compositionLocalOf<AppTypography> { error("No AppTypography provided") }
val LocalAppSpacing = compositionLocalOf<AppSpacing> { error("No AppSpacing provided") }

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
// 4 theme variants
// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

val defaultTypography = AppTypography(
    heading = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    body = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
    button = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
    caption = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal),
)

val defaultSpacing = AppSpacing(xs = 4.dp, sm = 8.dp, md = 16.dp, lg = 24.dp, xl = 32.dp)

val standardLight = AppThemeConfig(
    colors = AppColors(
        primary = Color(0xFF1A73E8),
        onPrimary = Color.White,
        secondary = Color(0xFF34A853),
        background = Color(0xFFF8F9FA),
        surface = Color.White,
        text = Color(0xFF202124),
        textSecondary = Color(0xFF5F6368),
    ),
    typography = defaultTypography,
    spacing = defaultSpacing,
)

val standardDark = AppThemeConfig(
    colors = AppColors(
        primary = Color(0xFF8AB4F8),
        onPrimary = Color(0xFF062E6F),
        secondary = Color(0xFF81C995),
        background = Color(0xFF202124),
        surface = Color(0xFF303134),
        text = Color(0xFFE8EAED),
        textSecondary = Color(0xFF9AA0A6),
    ),
    typography = defaultTypography,
    spacing = defaultSpacing,
)

val primeLight = AppThemeConfig(
    colors = AppColors(
        primary = Color(0xFF6C5CE7),
        onPrimary = Color.White,
        secondary = Color(0xFFE84393),
        background = Color(0xFFFAF9FF),
        surface = Color.White,
        text = Color(0xFF2D2D3F),
        textSecondary = Color(0xFF6B6B80),
    ),
    typography = defaultTypography,
    spacing = defaultSpacing,
)

val primeDark = AppThemeConfig(
    colors = AppColors(
        primary = Color(0xFFA29BFE),
        onPrimary = Color(0xFF1A1040),
        secondary = Color(0xFFFF6B81),
        background = Color(0xFF16162A),
        surface = Color(0xFF252544),
        text = Color(0xFFE8E8F0),
        textSecondary = Color(0xFF9E9EB8),
    ),
    typography = defaultTypography,
    spacing = defaultSpacing,
)

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
// Your custom DS components (no Material)
// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

@Composable
fun AppButton(label: String, modifier: Modifier = Modifier) {
    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current
    val spacing = LocalAppSpacing.current

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(colors.primary)
            .padding(horizontal = spacing.md, vertical = spacing.sm),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, style = typography.button, color = colors.onPrimary)
    }
}

@Composable
fun AppCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current
    val spacing = LocalAppSpacing.current

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(colors.surface)
            .padding(spacing.md),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            Text(title, style = typography.heading, color = colors.text)
            Text(subtitle, style = typography.body, color = colors.textSecondary)
        }
    }
}

@Composable
fun AppBadge(label: String, modifier: Modifier = Modifier) {
    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current
    val spacing = LocalAppSpacing.current

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(colors.secondary)
            .padding(horizontal = spacing.sm, vertical = spacing.xs),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, style = typography.caption, color = Color.White)
    }
}