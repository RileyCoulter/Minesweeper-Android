package com.example.battlesweeper.GameState


enum class GameStatus {
    NotStarted,
    Playing,
    Won,
    Lost
}

data class TileState(
    val isMine: Boolean,
    val isRevealed: Boolean,
    val isFlagged: Boolean,
    val adjacentMines: Int
)


data class GameState(
    val board: List<List<TileState>>,
    val minesRemaining: Int,
    val startTime: Long?,
    val elapsedSeconds: Int,
    val flagMode: Boolean,
    val gameStatus: GameStatus,
    val gameSettings: BattleSweeper_GameSettings
)

data class Position(
    val row: Int,
    val col: Int
)













