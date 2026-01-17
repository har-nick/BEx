package uk.co.harnick.bex.presentation.components.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.co.harnick.bex.presentation.icons.Icons
import uk.co.harnick.bex.presentation.icons.Search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onUpdateQuery: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val searchBarState = rememberSearchBarState()

    SearchBar(
        state = searchBarState,
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onUpdateQuery,
                onSearch = {},
                expanded = false,
                onExpandedChange = {},
                placeholder = {
                    Text("Search for artists, albums, or tracks...")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Search,
                        contentDescription = null
                    )
                }
            )
        },
        modifier = modifier
    )
}
