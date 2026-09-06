package com.rileycoulter.battlesweeper.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rileycoulter.battlesweeper.ui.screens.PlayBattleSweeperScreen

@Composable
fun BattleSweeperNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BattleSweeperDestination.MainMenu
    ) {
        composable<BattleSweeperDestination.MainMenu> {
            PlaceholderScreen(
                title = "Main Menu",
                buttonText = "Go to Game",
                onButtonClick = {
                    navController.navigate(BattleSweeperDestination.Game)
                }
            )
        }

        composable<BattleSweeperDestination.Game> {
            PlayBattleSweeperScreen()
        }

        composable<BattleSweeperDestination.Results> {
            PlaceholderScreen("Results")
        }
    }
}

@Composable
private fun PlaceholderScreen(
    title: String,
    buttonText: String? = null,
    onButtonClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title)

        if (buttonText != null && onButtonClick != null) {
            Button(
                onClick = onButtonClick
            ) {
                Text(text = buttonText)
            }
        }
    }
}

@Composable
private fun PlaceholderScreen(
    title: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title)
    }
}