package uk.co.harnick.bex.presentation.components.login

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink

@Composable
fun HelpLink() {
    val styledLink = buildAnnotatedString {
        append("For help finding your token ")

        withLink(
            link = LinkAnnotation.Url(
                url = "https://github.com/har-nick/BandKit/wiki/Your-Token#desktop-web",
                styles = TextLinkStyles(
                    style = SpanStyle(color = MaterialTheme.colorScheme.primary)
                ),
            )
        ) {
            append("click here")
        }

        append(".")
    }

    Text(styledLink)
}
