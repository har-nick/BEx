package uk.co.harnick.bex.presentation.components

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.request.crossfade
import okio.Path

@Composable
fun setCoilImageLoader(
    path: Path
) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(300)
            .diskCache {
                DiskCache.Builder()
                    .directory(path)
                    .build()
            }
            .build()
    }
}
