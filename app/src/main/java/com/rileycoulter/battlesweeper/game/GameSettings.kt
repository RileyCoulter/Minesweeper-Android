package com.rileycoulter.battlesweeper.game

data class BattleSweeper_GameSettings (
    val rows: Int = 23,
    val columns: Int = 12,
    val mines: Int = 10,
    val guaranteedStartingSize: Int = 1
)

object BattleSweeper_GameSettings_Presets {
    val Beginner = BattleSweeper_GameSettings(23, 12, 41, 10)
    val Intermediate = BattleSweeper_GameSettings(23, 12, 20, 10)
    val Expert = BattleSweeper_GameSettings(23, 12, 30, 10)
}