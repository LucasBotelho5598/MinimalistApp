package com.example.todoapp.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CircleCanvas() {

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
            .size(20.dp)
    ) {
        translate(left = 130f, top = 110f) {
            scale(scaleX = 0.4f, scaleY = 0.4f) {
                drawCircle(Color(0xFF6AE0D9).copy(0.5f), radius = 200.dp.toPx())
            }
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)
            .wrapContentSize(Alignment.TopStart)
            .size(5.dp)
    )
    {
        translate(left = 40f, top = 80f) {
            scale(scaleX = 0.35f, scaleY = 0.35f) {
                drawCircle(Color(0xFF6AE0D9).copy(0.5f), radius = 190.dp.toPx())
            }
        }
    }


}