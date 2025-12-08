package uk.co.harnick.bex.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorReportMenu(
    error: Throwable,
    onDismiss: () -> Unit
) {
    Scrim {
        Surface(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .fillMaxWidth(0.7F)
                .fillMaxHeight(0.85F)
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("An Error Occurred") }
                    )
                }
            ) { scaffoldPadding ->
                Column(
                    modifier = Modifier
                        .padding(scaffoldPadding)
                        .padding(all = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.large)
                            .weight(1F),
                        tonalElevation = LocalAbsoluteTonalElevation.current + 2.dp
                    ) {
                        Text(
                            text = error.stackTraceToString(),
                            modifier = Modifier.padding(all = 10.dp),
                            softWrap = false
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = { onDismiss() },
                            content = { Text("Cancel") }
                        )

                        TextButton(
                            onClick = { },
                            content = { Text("Report") }
                        )
                    }
                }
            }
        }
    }
}
