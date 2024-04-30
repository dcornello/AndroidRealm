package com.dcornello.androidrealm

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dcornello.androidrealm.ui.theme.AndroidRealmTheme

@Composable
fun BackgroundWaveView(
    modifier: Modifier = Modifier,
    topWaveColor: Color = Color.Black,
    bottomWaveColor: Color = Color.White
) {
    Box(modifier = modifier) {
        val screenWidthInPx = LocalConfiguration.current.screenWidthDp.dp.value * LocalDensity.current.density

        val x0 by remember { mutableFloatStateOf(0f) }
        val y0 by remember { mutableFloatStateOf(screenWidthInPx / 2) }

        val x1 by remember { mutableFloatStateOf(screenWidthInPx / 3f) }
        val y1 by remember { mutableFloatStateOf(600f) }
        val x2 by remember { mutableFloatStateOf(screenWidthInPx / 2f) }
        val y2 by remember { mutableFloatStateOf(0f) }
        val x3 by remember { mutableFloatStateOf(screenWidthInPx) }
        val y3 by remember { mutableFloatStateOf(200f) }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(bottomWaveColor)
        ) {
            Path().apply {
                reset()
                lineTo(x = x0, y = y0)
                cubicTo(x1 = x1, y1 = y1, x2 = x2, y2 = y2, x3 = x3, y3 = y3)
                lineTo(x = x3, y = 0f)

                drawPath(
                    color = topWaveColor,
                    path = this,
                )
            }
        }
    }
}

@Composable
@Preview
fun TestWavePreview() {
    AndroidRealmTheme {
        Surface {
            BackgroundWaveView()
        }
    }
}