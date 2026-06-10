package com.gymroutine.app.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = GymAccent,
    onPrimary = GymBlack,
    primaryContainer = GymAccentSubtle,
    onPrimaryContainer = GymAccent,
    secondary = GymBlue,
    onSecondary = GymBlack,
    secondaryContainer = Color(0xFF0D2625),
    onSecondaryContainer = GymBlue,
    tertiary = GymOrange,
    onTertiary = GymBlack,
    tertiaryContainer = Color(0xFF2A1508),
    onTertiaryContainer = GymOrange,
    background = GymBlack,
    onBackground = GymTextPrimary,
    surface = GymDarkSurface,
    onSurface = GymTextPrimary,
    surfaceVariant = GymCard,
    onSurfaceVariant = GymTextSecondary,
    surfaceContainer = GymCard,
    surfaceContainerHigh = GymCardElevated,
    surfaceContainerHighest = Color(0xFF2E2E2E),
    outline = GymBorder,
    outlineVariant = GymDivider,
    error = GymRed,
    onError = Color.White,
    errorContainer = Color(0xFF2A0A0A),
    onErrorContainer = GymRed,
    scrim = Color(0xCC000000),
    inverseSurface = GymTextPrimary,
    inverseOnSurface = GymBlack,
    inversePrimary = GymBlack
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1A1A1A),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE8FF4D),
    onPrimaryContainer = Color(0xFF0A0A0A),
    secondary = Color(0xFF00796B),
    onSecondary = Color.White,
    background = LightBackground,
    onBackground = Color(0xFF1A1A1A),
    surface = LightSurface,
    onSurface = Color(0xFF1A1A1A),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF616161),
    outline = Color(0xFFE0E0E0),
    error = GymRed,
    onError = Color.White
)

val LocalDarkMode = compositionLocalOf { true }

@Composable
fun GymRoutineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(LocalDarkMode provides darkTheme) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
