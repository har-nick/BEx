package uk.co.harnick.bex.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uk.co.harnick.bex.presentation.state.SnackbarData

object MainScaffold {
    @Composable
    operator fun invoke(
        topBar: @Composable () -> Unit,
        snackbarData: Flow<SnackbarData>,
        content: @Composable (PaddingValues) -> Unit
    ) {
        val snackbarScope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        snackbarData
            .filterNotNull()
            .onEach { it.show(snackbarHostState) }
            .launchIn(snackbarScope)

        Scaffold(
            topBar = { topBar() },
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.padding(bottom = 24.dp),
                    snackbar = {
                        // TODO
                        Snackbar(it)
                    }
                )
            },
            content = content
        )
    }
}