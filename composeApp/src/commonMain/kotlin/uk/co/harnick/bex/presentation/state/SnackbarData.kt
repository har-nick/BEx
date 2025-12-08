package uk.co.harnick.bex.presentation.state

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarDuration.Short
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.material3.SnackbarResult.Dismissed
import androidx.compose.material3.SnackbarVisuals

class SnackbarData(
    override val message: String,
    override val withDismissAction: Boolean = true,
    override val duration: SnackbarDuration = Short,
    private val action: ActionData? = null
) : SnackbarVisuals {
    class ActionData(
        val label: String,
        val onClick: (() -> Unit)
    )

    override val actionLabel: String? = action?.label

    suspend fun show(snackbarHostState: SnackbarHostState) {
        val result = snackbarHostState.showSnackbar(this)
        when (result) {
            Dismissed -> {}
            ActionPerformed -> action?.onClick()
        }
    }
}
