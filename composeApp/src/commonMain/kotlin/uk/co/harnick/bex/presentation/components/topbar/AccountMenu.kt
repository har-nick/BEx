package uk.co.harnick.bex.presentation.components.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.presentation.icons.ExitToApp
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.icons.Settings
import uk.co.harnick.bex.presentation.state.LocalAccount

@Composable
fun AccountMenu(
    onToggleSettingsMenu: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable { isExpanded = !isExpanded }
        ) {
            content()
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.width(IntrinsicSize.Min)
        ) {
            LocalAccount.current?.let { account ->
                Text(
                    text = account.displayName,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            DropdownMenuItem(
                text = { Text("Settings") },
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Settings,
                        contentDescription = null
                    )
                },
                onClick = {
                    isExpanded = false
                    onToggleSettingsMenu()
                }
            )

            DropdownMenuItem(
                text = { Text("Logout") },
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.ExitToApp,
                        contentDescription = null
                    )
                },
                onClick = {
                    isExpanded = false
                    onLogout()
                }
            )
        }
    }
}
