package uk.co.harnick.bex.presentation.state

import androidx.compose.runtime.compositionLocalOf
import uk.co.harnick.bex.domain.model.Settings

val LocalSettings = compositionLocalOf<Settings> { error("No settings provided.") }