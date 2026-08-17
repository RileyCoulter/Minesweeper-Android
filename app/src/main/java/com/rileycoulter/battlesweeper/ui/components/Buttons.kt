package com.rileycoulter.battlesweeper.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rileycoulter.battlesweeper.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun ResetButton(
    onClick: () -> Unit,
    buttonHeight: Int,
    buttonWidth: Int,
    modifier: Modifier = Modifier
) {
    //TODO: add state watcher to be able to change image based on state of game

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    Image(
        painter = painterResource(
            if (pressed)
                R.drawable.smilefacedown
            else
                R.drawable.smileface
        ),
        contentDescription = "Reset Button",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,   // Removes the ripple effect (optional)
                onClick = {onClick()} )
            .width(buttonWidth.dp)
            .height(buttonHeight.dp)
    )
}




@Composable
fun ToggleFlagButton(
    flagMode: Boolean,
    onClick: () -> Unit,
    buttonDiameter: Int,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()


    val buttonSize = buttonDiameter.dp
    val buttonRadius = buttonSize / 2
    val circleRadius = buttonRadius * 0.72f
    val depthOffset = buttonSize * 0.023f
    val iconSize = buttonSize * 0.65f

    Box(
        modifier = modifier
            .size(buttonSize)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier.size(buttonSize)
        ) {
            val radius = circleRadius.toPx()
            val offset = depthOffset.toPx()

            // Swap the highlight/shadow colors when pressed
            val topLeftColor = if (pressed) {
                Color.Gray
            } else {
                Color.White
            }
            val bottomRightColor = if (pressed) {
                Color.White
            } else {
                Color.Gray
            }
            val centerColor = if (pressed) {
                Color(0xFFC0C0C0)
            } else {
                Color.LightGray
            }

            // Top-left 3D effect
            drawCircle(
                color = topLeftColor,
                radius = radius,
                center = Offset(
                    x = size.width / 2 - offset,
                    y = size.height / 2 - offset
                )
            )

            // Bottom-right 3D effect
            drawCircle(
                color = bottomRightColor,
                radius = radius,
                center = Offset(
                    x = size.width / 2 + offset,
                    y = size.height / 2 + offset
                )
            )

            // Main button surface
            drawCircle(
                color = centerColor,
                radius = radius,
                center = Offset(
                    x = size.width / 2,
                    y = size.height / 2
                )
            )
        }

        Image(
            painter = painterResource(
                if (flagMode) {
                    R.drawable.flag
                } else {
                    R.drawable.mine
                }
            ),
            contentDescription = "Flag Mode Button",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(iconSize)
        )
    }
}

