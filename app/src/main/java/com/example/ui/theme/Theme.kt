package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DeveloperDarkColorScheme = darkColorScheme(
    primary = HtmlOrangePrimary,
    onPrimary = Color.White,
    primaryContainer = HtmlOrangeDark,
    onPrimaryContainer = Color.White,
    secondary = TechCyan,
    onSecondary = Color.Black,
    secondaryContainer = DarkSurfaceVariant,
    onSecondaryContainer = TechCyanLight,
    tertiary = SuccessGreen,
    onTertiary = Color.White,
    background = DarkBackground,
    onBackground = Color(0xFFF1F5F9),
    surface = DarkSurface,
    onSurface = Color(0xFFF8FAFC),
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = DarkBorder
)

val AmoledColorScheme = darkColorScheme(
    primary = HtmlOrangePrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF7C2D12),
    onPrimaryContainer = HtmlOrangeLight,
    secondary = TechCyan,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF0F172A),
    onSecondaryContainer = TechCyanLight,
    tertiary = SuccessGreen,
    onTertiary = Color.White,
    background = AmoledBackground,
    onBackground = Color(0xFFFFFFFF),
    surface = AmoledSurface,
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = AmoledSurfaceVariant,
    onSurfaceVariant = Color(0xFFD4D4D8),
    outline = Color(0xFF27272A)
)

val CleanLightColorScheme = lightColorScheme(
    primary = HtmlOrangeDark,
    onPrimary = Color.White,
    primaryContainer = HtmlOrangeLight,
    onPrimaryContainer = Color(0xFF431407),
    secondary = TechCyanDark,
    onSecondary = Color.White,
    secondaryContainer = TechCyanLight,
    onSecondaryContainer = Color(0xFF082F49),
    tertiary = SuccessGreenDark,
    onTertiary = Color.White,
    background = LightBackground,
    onBackground = Color(0xFF0F172A),
    surface = LightSurface,
    onSurface = Color(0xFF0F172A),
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = Color(0xFF334155),
    outline = LightBorder
)

val HighContrastColorScheme = darkColorScheme(
    primary = Color(0xFFFFA500),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFFFFCC00),
    onPrimaryContainer = Color.Black,
    secondary = Color(0xFF00FFFF),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF003366),
    onSecondaryContainer = Color.White,
    tertiary = Color(0xFF00FF66),
    onTertiary = Color.Black,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color(0xFF1A1A1A),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF2A2A2A),
    onSurfaceVariant = Color.White,
    outline = Color.White
)

@Composable
fun LearnHtmlTheme(
    themeMode: String = "DEVELOPER_DARK", // "SYSTEM", "LIGHT", "DEVELOPER_DARK", "AMOLED", "HIGH_CONTRAST"
    content: @Composable () -> Unit
) {
    val systemDark = isSystemInDarkTheme()
    val colorScheme = when (themeMode) {
        "LIGHT" -> CleanLightColorScheme
        "DEVELOPER_DARK" -> DeveloperDarkColorScheme
        "AMOLED" -> AmoledColorScheme
        "HIGH_CONTRAST" -> HighContrastColorScheme
        "SYSTEM" -> if (systemDark) DeveloperDarkColorScheme else CleanLightColorScheme
        else -> DeveloperDarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
