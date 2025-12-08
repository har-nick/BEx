package uk.co.harnick.bex.presentation.components.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.presentation.icons.BandcampLogo
import uk.co.harnick.bex.presentation.icons.Icons

@Composable
fun BandcampLogo() {
    Image(
        imageVector = Icons.BandcampLogo,
        contentDescription = null,
        modifier = Modifier.size(72.dp)
    )
}
