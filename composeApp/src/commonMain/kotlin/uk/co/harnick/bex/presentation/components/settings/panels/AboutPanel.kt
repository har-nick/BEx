package uk.co.harnick.bex.presentation.components.settings.panels

import BEx.composeApp.BuildConfig
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import uk.co.harnick.bex.presentation.icons.GithubLogo
import uk.co.harnick.bex.presentation.icons.Icons

@Composable
fun AboutPanel() {
    val uriHandler = LocalUriHandler.current

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Surface(
            shape = CardDefaults.shape
        ) {
            ListItem(
                headlineContent = { Text("Version") },
                supportingContent = {
                    Text(
                        text = BuildConfig.VERSION,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            )
        }

        Text(
            text = "Links",
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        Surface(
            shape = CardDefaults.shape
        ) {
            ListItem(
                headlineContent = { Text("Github") },
                modifier = Modifier
                    .clickable { uriHandler.openUri("https://github.com/har-nick/BEx") }
                    .pointerHoverIcon(PointerIcon.Hand),
                supportingContent = {
                    Text(
                        text = "har-nick/BEx",
                        modifier = Modifier.padding(top = 4.dp)
                    )
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.GithubLogo,
                        modifier = Modifier.size(40.dp),
                        contentDescription = null
                    )
                }
            )
        }

        Text(
            text = "Libraries",
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        Surface(
            shape = CardDefaults.shape
        ) {
            LazyColumn {
                item {
                    ListItem(
                        headlineContent = { Text("BandKit") },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri("https://github.com/har-nick/BandKit")
                            }
                            .pointerHoverIcon(PointerIcon.Hand),
                        supportingContent = {
                            Text(
                                text = "A Kotlin Multiplatform library to interface with Bandcamp's API",
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("Coil") },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri("https://github.com/coil-kt/coil")
                            }
                            .pointerHoverIcon(PointerIcon.Hand),
                        supportingContent = {
                            Text(
                                text = "Image loading for Android and Compose Multiplatform.",
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("Compose Multiplatform") },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri("https://github.com/JetBrains/compose-multiplatform")
                            }
                            .pointerHoverIcon(PointerIcon.Hand),
                        supportingContent = {
                            Text(
                                text = "Compose Multiplatform, a modern UI framework for Kotlin that makes building performant and beautiful user interfaces easy and enjoyable. ",
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("FileKit") },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri("https://github.com/vinceglb/FileKit")
                            }
                            .pointerHoverIcon(PointerIcon.Hand),
                        supportingContent = {
                            Text(
                                text = "Pick and save Files, Medias and Folder for Kotlin Multiplatform / KMP and Compose Multiplatform / CMP",
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("kotlinx.coroutines") },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri("https://github.com/Kotlin/kotlinx.coroutines")
                            }
                            .pointerHoverIcon(PointerIcon.Hand),
                        supportingContent = {
                            Text(
                                text = "Library support for Kotlin coroutines",
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("KStore") },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri("https://github.com/xxfast/KStore")
                            }
                            .pointerHoverIcon(PointerIcon.Hand),
                        supportingContent = {
                            Text(
                                text = "A tiny Kotlin multiplatform library that assists in saving and restoring objects to and from disk using kotlinx.coroutines, kotlinx.serialisation and kotlinx.io",
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("Ktor") },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri("https://github.com/ktorio/ktor")
                            }
                            .pointerHoverIcon(PointerIcon.Hand),
                        supportingContent = {
                            Text(
                                text = "Framework for quickly creating connected applications in Kotlin with minimal effort",
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("MaterialKolor") },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri("https://github.com/jordond/MaterialKolor")
                            }
                            .pointerHoverIcon(PointerIcon.Hand),
                        supportingContent = {
                            Text(
                                text = "Generate a dynamic Material3 color sheme from a seed color",
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    )
                }

                item {
                    ListItem(
                        headlineContent = { Text("Reorderable") },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri("https://github.com/Calvin-LL/Reorderable")
                            }
                            .pointerHoverIcon(PointerIcon.Hand),
                        supportingContent = {
                            Text(
                                text = "Reorder items in Lists and Grids in Jetpack Compose and Compose Multiplatform with drag and drop.",
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    )
                }
            }
        }
    }
}
