package com.example.vawcsanpedro2.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = PrimaryPink,
    onPrimary = White,
    primaryContainer = LightPink,
    onPrimaryContainer = TextDark,
    secondary = SoftPink,
    onSecondary = TextDark,
    secondaryContainer = VeryLightPink,
    onSecondaryContainer = TextDark,
    tertiary = AccentPink,
    onTertiary = White,
    tertiaryContainer = AccentRose,
    onTertiaryContainer = White,
    error = ErrorRed,
    onError = White,
    errorContainer = Color(0xFFFFEBEE),
    onErrorContainer = ErrorRed,
    background = White,
    onBackground = TextDark,
    surface = OffWhite,
    onSurface = TextDark,
    surfaceVariant = LightGray,
    onSurfaceVariant = TextMedium,
    outline = TextLight,
    inverseOnSurface = White,
    inverseSurface = TextDark,
    inversePrimary = AccentPink,
    surfaceTint = PrimaryPink,
    outlineVariant = TextLight,
    scrim = Color(0x52000000),
)

private val DarkColorScheme = darkColorScheme(
    primary = AccentPink,
    onPrimary = White,
    primaryContainer = PrimaryPink,
    onPrimaryContainer = White,
    secondary = AccentRose,
    onSecondary = White,
    secondaryContainer = SoftPink,
    onSecondaryContainer = TextDark,
    tertiary = AccentCoral,
    onTertiary = White,
    tertiaryContainer = LightPink,
    onTertiaryContainer = TextDark,
    error = ErrorRed,
    onError = White,
    errorContainer = Color(0xFF4A1C1C),
    onErrorContainer = Color(0xFFFFB4AB),
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    outline = Color(0xFF938F99),
    inverseOnSurface = Color(0xFF1C1B1F),
    inverseSurface = Color(0xFFE6E1E5),
    inversePrimary = AccentPink,
    surfaceTint = AccentPink,
    outlineVariant = Color(0xFF49454F),
    scrim = Color(0x52000000),
)

@Composable
fun VawcSanPedro2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}