package com.rileycoulter.battlesweeper.ui.navigation


import kotlinx.serialization.Serializable

sealed interface BattleSweeperDestination {

    @Serializable
    data object MainMenu : BattleSweeperDestination

    @Serializable
    data object Game : BattleSweeperDestination

    @Serializable
    data object Results : BattleSweeperDestination
}