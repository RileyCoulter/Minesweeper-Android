package com.rileycoulter.battlesweeper.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rileycoulter.battlesweeper.ViewModel.BattleSweeperViewModel
import com.rileycoulter.battlesweeper.ui.components.BS_Border
import com.rileycoulter.battlesweeper.ui.components.ResetButton
import com.rileycoulter.battlesweeper.ui.components.SevenSegmentCounter
import com.rileycoulter.battlesweeper.ui.components.SevenSegmentTimer
import com.rileycoulter.battlesweeper.ui.components.TileGrid
import com.rileycoulter.battlesweeper.ui.components.ToggleFlagButton
import com.rileycoulter.battlesweeper.util.UIConstants
import androidx.lifecycle.viewmodel.compose.viewModel

class PlayBattleSweeper : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen()
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "GameScreen")
@Composable
fun LoginScreen(
    viewModel: BattleSweeperViewModel = viewModel()
) {
    val gameState by viewModel.gameState.collectAsState()

    LaunchedEffect(Unit) {
//        viewModel.startNewGame(
//            settings = BattleSweeper_GameSettings_Presets.Beginner,
//            startingPosition = Position(4, 4)
//        )
    }

    val density = LocalDensity.current
    val borderHeight_dp = 8.dp
    val borderHeight_px = with(density) {
        borderHeight_dp.toPx()
    }

    Box(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(Color.Companion.Black),
        //contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()

        ) {
            Row(
                modifier = Modifier.Companion
                    .fillMaxHeight(0.12f)
                    .fillMaxWidth()
                    .padding(top = 23.dp)
                    .background(color = Color.Companion.LightGray)
                    .BS_Border(
                        lightGray = UIConstants.BORDERCOLOR_WHITE,
                        medGray = UIConstants.BORDERCOLOR_LIGHTGRAY,
                        darkGray = UIConstants.BORDERCOLOR_GRAY,
                        borderWidth = borderHeight_px
                    )
            ) {
                Box(
                    modifier = Modifier.Companion
                        .align(Alignment.Companion.CenterVertically)
                        .padding(start = 30.dp)
                )
                {
                    SevenSegmentCounter(gameState.minesRemaining, 3, 40, 70, Modifier.Companion)
                }


                Spacer(Modifier.Companion.width(35.dp))


                Box(
                    modifier = Modifier.Companion
                        .align(Alignment.Companion.CenterVertically)
                )
                {
                    ResetButton(
                        onClick = viewModel::onResetButtonClickHandler,
                        buttonHeight = 40,
                        buttonWidth = 40,
                        modifier = Modifier.Companion
                    )
                }


                Spacer(Modifier.Companion.width(35.dp))

                Box(
                    modifier = Modifier.Companion
                        .align(Alignment.Companion.CenterVertically)
                )
                {
                    ToggleFlagButton(
                        flagMode = true,
                        onClick = viewModel::onFlagModeButtonClickHandler,
                        buttonDiameter = 40,
                        modifier = Modifier.Companion.size(40.dp)
                    )

                }

                Spacer(Modifier.Companion.width(35.dp))

                Box(
                    modifier = Modifier.Companion
                        .align(Alignment.Companion.CenterVertically)
                )
                {
                    SevenSegmentTimer(gameState.elapsedSeconds, 2, 40, 100, Modifier.Companion)
                    //SevenSegmentColon(60, 20)
                }


            }


            Box(
                modifier = Modifier.Companion
                    .BS_Border(
                        lightGray = UIConstants.BORDERCOLOR_WHITE,
                        medGray = UIConstants.BORDERCOLOR_LIGHTGRAY,
                        darkGray = UIConstants.BORDERCOLOR_GRAY,
                        borderWidth = borderHeight_px
                    ),
                contentAlignment = Alignment.Companion.Center
            )
            {
                Box(
                    modifier = Modifier.Companion
                        .padding(borderHeight_dp)
                ) {
                    TileGrid(
                        board = gameState.board,
                        onTileClick = viewModel::onTileClick,
                        onTileLongClick = viewModel::onTileLongClick,
                        tileWidth = 33,
                        tileHeight = 33
                    )
                }
            }
        }
    }

}

