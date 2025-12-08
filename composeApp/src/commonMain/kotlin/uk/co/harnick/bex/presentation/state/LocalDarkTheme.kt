package uk.co.harnick.bex.presentation.state

import androidx.compose.runtime.compositionLocalOf

val LocalDarkTheme = compositionLocalOf<Boolean> { error("No dark theme value provided.") }