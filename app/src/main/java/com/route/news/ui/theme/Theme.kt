package com.route.news.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,

    background = White,
    onBackground = Black,

    surface = White,
    onSurface = Black,

    secondary = Grey,
    onSecondary = Black
)

@Composable
fun NewsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}