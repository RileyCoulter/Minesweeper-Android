package com.rileycoulter.battlesweeper.game


data class GameState(
    val board: List<List<TileState>>,
    val minesRemaining: Int,
    val startTime: Long?,
    val elapsedSeconds: Int,
    val flagMode: Boolean,
    val gameStatus: GameStatus,
    val gameSettings: BattleSweeper_GameSettings
)

