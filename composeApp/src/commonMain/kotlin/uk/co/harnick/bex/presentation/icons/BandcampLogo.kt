package uk.co.harnick.bex.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.BandcampLogo: ImageVector
    get() {
        if (_BandcampLogo != null) {
            return _BandcampLogo!!
        }
        _BandcampLogo = ImageVector.Builder(
            name = "BandcampLogo",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF1DA0C3))) {
                moveToRelative(225.5f, 1.6f)
                curveToRelative(-6.1f, 0.7f, -15.7f, 2.3f, -21.5f, 3.4f)
                curveToRelative(-5.8f, 1.2f, -15.6f, 3.7f, -21.8f, 5.6f)
                curveToRelative(-6.1f, 1.9f, -15.8f, 5.2f, -21.4f, 7.5f)
                curveToRelative(-5.7f, 2.2f, -15.9f, 7f, -22.8f, 10.5f)
                curveToRelative(-6.9f, 3.6f, -17.5f, 9.8f, -23.5f, 13.8f)
                curveToRelative(-6f, 4.1f, -14.8f, 10.4f, -19.5f, 14.2f)
                curveToRelative(-4.7f, 3.7f, -13.7f, 12f, -20.1f, 18.3f)
                curveToRelative(-6.3f, 6.4f, -14.6f, 15.4f, -18.3f, 20.1f)
                curveToRelative(-3.8f, 4.7f, -10.1f, 13.5f, -14.2f, 19.5f)
                curveToRelative(-4f, 6f, -10.2f, 16.6f, -13.8f, 23.5f)
                curveToRelative(-3.5f, 6.9f, -8.3f, 17.1f, -10.5f, 22.8f)
                curveToRelative(-2.3f, 5.6f, -5.6f, 15.3f, -7.5f, 21.4f)
                curveToRelative(-1.9f, 6.2f, -4.3f, 15.8f, -5.5f, 21.3f)
                curveToRelative(-1.1f, 5.5f, -2.7f, 15.8f, -3.6f, 23f)
                curveToRelative(-0.8f, 7.2f, -1.5f, 20.4f, -1.5f, 29.5f)
                curveToRelative(0f, 9.1f, 0.7f, 22.4f, 1.5f, 29.5f)
                curveToRelative(0.9f, 7.1f, 2.5f, 17.5f, 3.6f, 23f)
                curveToRelative(1.2f, 5.5f, 3.6f, 15.1f, 5.5f, 21.3f)
                curveToRelative(1.9f, 6.1f, 5.2f, 15.8f, 7.5f, 21.4f)
                curveToRelative(2.2f, 5.7f, 7f, 15.9f, 10.5f, 22.8f)
                curveToRelative(3.6f, 6.9f, 9.8f, 17.4f, 13.8f, 23.5f)
                curveToRelative(4.1f, 6.1f, 10.4f, 14.8f, 14.2f, 19.5f)
                curveToRelative(3.7f, 4.7f, 12f, 13.7f, 18.3f, 20.1f)
                curveToRelative(6.4f, 6.3f, 15.4f, 14.6f, 20.1f, 18.3f)
                curveToRelative(4.7f, 3.8f, 13.5f, 10.1f, 19.5f, 14.2f)
                curveToRelative(6f, 4f, 16.6f, 10.2f, 23.5f, 13.8f)
                curveToRelative(6.9f, 3.5f, 17.1f, 8.3f, 22.8f, 10.5f)
                curveToRelative(5.6f, 2.3f, 15.3f, 5.6f, 21.4f, 7.5f)
                curveToRelative(6.2f, 1.9f, 15.8f, 4.3f, 21.3f, 5.5f)
                curveToRelative(5.5f, 1.1f, 15.8f, 2.7f, 23f, 3.6f)
                curveToRelative(7.2f, 0.8f, 20.4f, 1.5f, 29.5f, 1.5f)
                curveToRelative(9.1f, 0f, 22.4f, -0.7f, 29.5f, -1.5f)
                curveToRelative(7.1f, -0.9f, 17.5f, -2.5f, 23f, -3.6f)
                curveToRelative(5.5f, -1.2f, 15.1f, -3.6f, 21.3f, -5.5f)
                curveToRelative(6.1f, -1.9f, 15.8f, -5.2f, 21.4f, -7.5f)
                curveToRelative(5.7f, -2.2f, 15.9f, -7f, 22.8f, -10.5f)
                curveToRelative(6.9f, -3.6f, 17.4f, -9.8f, 23.5f, -13.8f)
                curveToRelative(6.1f, -4.1f, 14.8f, -10.4f, 19.5f, -14.2f)
                curveToRelative(4.7f, -3.7f, 13.7f, -12f, 20.1f, -18.3f)
                curveToRelative(6.3f, -6.4f, 14.6f, -15.4f, 18.3f, -20.1f)
                curveToRelative(3.8f, -4.7f, 10.1f, -13.4f, 14.2f, -19.5f)
                curveToRelative(4f, -6.1f, 10.2f, -16.6f, 13.8f, -23.5f)
                curveToRelative(3.5f, -6.9f, 8.3f, -17.1f, 10.5f, -22.8f)
                curveToRelative(2.3f, -5.6f, 5.6f, -15.3f, 7.5f, -21.5f)
                curveToRelative(1.9f, -6.1f, 4.3f, -15.7f, 5.5f, -21.2f)
                curveToRelative(1.1f, -5.5f, 2.7f, -15.9f, 3.6f, -23f)
                curveToRelative(0.8f, -7.1f, 1.5f, -20.4f, 1.5f, -29.5f)
                curveToRelative(0f, -9.1f, -0.7f, -22.3f, -1.5f, -29.5f)
                curveToRelative(-0.9f, -7.2f, -2.5f, -17.5f, -3.6f, -23f)
                curveToRelative(-1.2f, -5.5f, -3.6f, -15.1f, -5.5f, -21.3f)
                curveToRelative(-1.9f, -6.1f, -5.2f, -15.8f, -7.5f, -21.5f)
                curveToRelative(-2.2f, -5.6f, -7f, -15.8f, -10.5f, -22.7f)
                curveToRelative(-3.6f, -6.9f, -9.8f, -17.5f, -13.8f, -23.5f)
                curveToRelative(-4.1f, -6f, -10.4f, -14.8f, -14.2f, -19.5f)
                curveToRelative(-3.7f, -4.7f, -12f, -13.7f, -18.3f, -20.1f)
                curveToRelative(-6.4f, -6.3f, -15.4f, -14.6f, -20.1f, -18.3f)
                curveToRelative(-4.7f, -3.8f, -13.4f, -10.1f, -19.5f, -14.2f)
                curveToRelative(-6.1f, -4f, -16.6f, -10.2f, -23.5f, -13.8f)
                curveToRelative(-6.9f, -3.5f, -17.1f, -8.3f, -22.8f, -10.5f)
                curveToRelative(-5.6f, -2.3f, -15.3f, -5.6f, -21.4f, -7.5f)
                curveToRelative(-6.2f, -1.9f, -15.8f, -4.3f, -21.3f, -5.5f)
                curveToRelative(-5.5f, -1.1f, -15.9f, -2.7f, -23f, -3.6f)
                curveToRelative(-7.6f, -0.9f, -20.4f, -1.5f, -31f, -1.4f)
                curveToRelative(-9.9f, 0.1f, -22.9f, 0.7f, -29f, 1.5f)
                close()
            }
            path(fill = SolidColor(Color.White)) {
                moveToRelative(204.2f, 179.3f)
                curveToRelative(-1.1f, 1.7f, -20.4f, 37.5f, -43.1f, 79.4f)
                curveToRelative(-22.6f, 42f, -41f, 76.5f, -40.8f, 76.8f)
                curveToRelative(0.1f, 0.3f, 39.6f, 0.7f, 87.7f, 1f)
                curveToRelative(48.1f, 0.2f, 89.9f, 0.1f, 92.8f, -0.3f)
                curveToRelative(5.1f, -0.7f, 5.5f, -0.9f, 8.2f, -5.7f)
                curveToRelative(1.5f, -2.8f, 4.9f, -9.1f, 7.5f, -14f)
                curveToRelative(2.7f, -5f, 5.1f, -9.5f, 5.5f, -10f)
                curveToRelative(0.4f, -0.6f, 3.3f, -6f, 6.5f, -12f)
                curveToRelative(3.2f, -6.1f, 6.1f, -11.5f, 6.5f, -12f)
                curveToRelative(0.4f, -0.6f, 3.3f, -6f, 6.5f, -12f)
                curveToRelative(3.2f, -6.1f, 6.1f, -11.5f, 6.5f, -12f)
                curveToRelative(0.4f, -0.6f, 2.9f, -5.1f, 5.5f, -10f)
                curveToRelative(2.6f, -5f, 10.4f, -19.4f, 17.2f, -32f)
                curveToRelative(6.9f, -12.7f, 14.5f, -26.7f, 16.9f, -31.3f)
                curveToRelative(2.4f, -4.5f, 4.4f, -8.4f, 4.4f, -8.7f)
                curveToRelative(0f, -0.3f, -41.8f, -0.5f, -92.9f, -0.5f)
                horizontalLineToRelative(-93f)
                close()
            }
        }.build()

        return _BandcampLogo!!
    }

@Suppress("ObjectPropertyName")
private var _BandcampLogo: ImageVector? = null
