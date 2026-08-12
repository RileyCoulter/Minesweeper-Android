package com.example.battlesweeper.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.battlesweeper.GameState.TileState
import com.example.battlesweeper.R


@Composable
fun Tile(
    tileState: TileState,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    tileHeight: Int,
    tileWidth: Int,
    modifier: Modifier = Modifier
    ) {
    val interactionSource = remember { MutableInteractionSource() }

    val pressed by interactionSource.collectIsPressedAsState()
    val isRevealed = tileState.isRevealed
    val isMine = tileState.isMine
    val isFlagged = tileState.isFlagged
    val adjacentMines = tileState.adjacentMines
    Image(
        painter = painterResource(
            when {
                //Tile not revealed
                !isRevealed && isFlagged -> R.drawable.masked_tile_flag
                !isRevealed && pressed -> R.drawable.revealed_tile
                !isRevealed -> R.drawable.masked_tile
                //Tile is revealed and is a mine
                isMine -> R.drawable.revealed_tile_bomb
                //Tile is revealed and is not a mine
                !isMine && isFlagged -> R.drawable.tile_not_mine
                else -> when (adjacentMines) {
                    0 -> R.drawable.revealed_tile
                    1 -> R.drawable.revealed_tile_1
                    2 -> R.drawable.revealed_tile_2
                    3 -> R.drawable.revealed_tile_3
                    4 -> R.drawable.revealed_tile_4
                    5 -> R.drawable.revealed_tile_5
                    6 -> R.drawable.revealed_tile_6
                    7 -> R.drawable.revealed_tile_7
                    8 -> R.drawable.revealed_tile_8
                    else ->  R.drawable.masked_tile_question_mark
                }
            }
        ),
        contentDescription = "Tile",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .combinedClickable(
                interactionSource = interactionSource,
                indication = null,   // Removes the ripple effect (optional)
                onClick = {onClick()},
                onLongClick = {onLongClick()})
            .width(tileWidth.dp)
            .height(tileHeight.dp)
    )
}