package uk.co.harnick.bex.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.FolderOpen: ImageVector
    get() {
        if (_FolderOpen != null) {
            return _FolderOpen!!
        }
        _FolderOpen = ImageVector.Builder(
            name = "FolderOpen",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color(0xFFE8EAED))) {
                moveTo(160f, 800f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(80f, 720f)
                verticalLineToRelative(-480f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(160f, 160f)
                horizontalLineToRelative(207f)
                quadToRelative(16f, 0f, 30.5f, 6f)
                reflectiveQuadToRelative(25.5f, 17f)
                lineToRelative(57f, 57f)
                horizontalLineToRelative(360f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(880f, 280f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(840f, 320f)
                lineTo(447f, 320f)
                lineToRelative(-80f, -80f)
                lineTo(160f, 240f)
                verticalLineToRelative(480f)
                lineToRelative(79f, -263f)
                quadToRelative(8f, -26f, 29.5f, -41.5f)
                reflectiveQuadTo(316f, 400f)
                horizontalLineToRelative(516f)
                quadToRelative(41f, 0f, 64.5f, 32.5f)
                reflectiveQuadTo(909f, 503f)
                lineToRelative(-72f, 240f)
                quadToRelative(-8f, 26f, -29.5f, 41.5f)
                reflectiveQuadTo(760f, 800f)
                lineTo(160f, 800f)
                close()
                moveTo(244f, 720f)
                horizontalLineToRelative(516f)
                lineToRelative(72f, -240f)
                lineTo(316f, 480f)
                lineToRelative(-72f, 240f)
                close()
                moveTo(160f, 458f)
                verticalLineToRelative(-218f)
                verticalLineToRelative(218f)
                close()
                moveTo(244f, 720f)
                lineTo(316f, 480f)
                lineTo(244f, 720f)
                close()
            }
        }.build()

        return _FolderOpen!!
    }

@Suppress("ObjectPropertyName")
private var _FolderOpen: ImageVector? = null
