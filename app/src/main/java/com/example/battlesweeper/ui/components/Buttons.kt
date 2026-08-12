package com.example.battlesweeper.ui.components

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
import com.example.battlesweeper.R


@Composable
fun ResetButton(
    onClick: () -> Unit,
    buttonHeight: Int,
    buttonWidth: Int,
    modifier: Modifier = Modifier
) {
    //TODO: add state watcher to be able to change image based on state of game\

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

    Image(
        painter = painterResource(
            if (flagMode)
                R.drawable.flag
            else
                R.drawable.mine
        ),
        contentDescription = "Flag Mode Button",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,   // Removes the ripple effect (optional)
                onClick = {onClick()} )
    )
}