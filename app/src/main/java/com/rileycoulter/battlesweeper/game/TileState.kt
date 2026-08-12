package com.rileycoulter.battlesweeper.game

data class TileState(
    val isMine: Boolean,
    val isRevealed: Boolean,
    val isFlagged: Boolean,
    val adjacentMines: Int
)