package com.example.battlesweeper.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.topBorder(
    color: Color,
    height: Float,
) = this.drawWithContent {
    drawContent()
    drawLine(
        color = color,
        start = Offset(0f, 0f),
        end = Offset(size.width, 0f),
        strokeWidth = height,
    )
}

fun Modifier.rightBorder(
    color: Color,
    width: Float,
) = this.drawWithContent {
    drawContent()
    drawLine(
        color = color,
        start = Offset(size.width, 0f),
        end = Offset(size.width, size.height),
        strokeWidth = width,
    )
}

fun Modifier.bottomBorder(
    color: Color,
    height: Float,
) = this.drawWithContent {
    drawContent()
    drawLine(
        color = color,
        start = Offset(0f, size.height),
        end = Offset(size.width, size.height),
        strokeWidth = height,
    )
}

fun Modifier.leftBorder(
    color: Color,
    width: Float,
) = this.drawWithContent {
    drawContent()
    drawLine(
        color = color,
        start = Offset(0f, 0f),
        end = Offset(0f, size.height),
        strokeWidth = width,
    )
}



fun Modifier.BS_Border(
    lightGray: Color,
    medGray: Color,
    darkGray: Color,
    borderWidth: Float,
) = this
    .graphicsLayer {
        compositingStrategy = CompositingStrategy.Offscreen
    }
    .drawWithContent {

    val triangle_tr = Path().apply {
        moveTo(size.width - (borderWidth / 2), borderWidth + borderWidth) //bottom right corner
        lineTo(size.width - (borderWidth / 2), 0f + (borderWidth / 2)) //top right corner
        lineTo(size.width - (borderWidth) - borderWidth, borderWidth + borderWidth) // bot left corner
        close()
    }

    val triangle_bl = Path().apply {
        moveTo((borderWidth / 2), size.height - borderWidth / 2) //bottom left corner
        lineTo((borderWidth + borderWidth), size.height - borderWidth / 2) // bottom right corner
        lineTo((borderWidth + borderWidth), size.height - borderWidth - borderWidth) //top right corner
        close()
    }
    drawRect(
        color = medGray,
        size = Size(
            width = size.width,
            height = size.height
        )
    )

    drawRect(
        color = darkGray,
        topLeft = Offset(0f + (borderWidth / 2), 0f + (borderWidth / 2)),
        size = Size(
            width = size.width - (borderWidth),
            height = size.height - (borderWidth)
        )
    )

    drawRect(
        color = lightGray,
        topLeft = Offset(0f + (borderWidth), 0f + (borderWidth)),
        size = Size(
            width = size.width - (borderWidth) - borderWidth / 2,
            height = size.height - (borderWidth) - borderWidth / 2
        )
    )

    drawPath(
        path = triangle_tr,
        color = lightGray,
        style = Fill
    )

    drawPath(
        path = triangle_bl,
        color = lightGray,
        style = Fill
    )

    drawRect(
        color = Color.Transparent,
        topLeft = Offset(0f + (borderWidth), 0f + (borderWidth)),
        size = Size(
            width = size.width - (borderWidth) - borderWidth,
            height = size.height - (borderWidth) - borderWidth
        ),
        blendMode = BlendMode.Clear,


        )
    drawContent()

}


