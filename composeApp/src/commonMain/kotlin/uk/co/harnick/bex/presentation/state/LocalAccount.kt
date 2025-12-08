package uk.co.harnick.bex.presentation.state

import androidx.compose.runtime.staticCompositionLocalOf
import uk.co.harnick.bex.domain.model.Account

val LocalAccount = staticCompositionLocalOf<Account?> { error("No account provided.") }
