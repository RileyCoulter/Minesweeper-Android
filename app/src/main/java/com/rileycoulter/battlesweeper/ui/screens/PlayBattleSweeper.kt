package com.rileycoulter.battlesweeper.ui.screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rileycoulter.battlesweeper.ViewModel.BattleSweeperViewModel
import com.rileycoulter.battlesweeper.ui.components.BS_Border
import com.rileycoulter.battlesweeper.ui.components.ResetButton
import com.rileycoulter.battlesweeper.ui.components.SevenSegmentCounter
import com.rileycoulter.battlesweeper.ui.components.SevenSegmentTimer
import com.rileycoulter.battlesweeper.ui.components.TileGrid
import com.rileycoulter.battlesweeper.ui.components.ToggleFlagButton
import com.rileycoulter.battlesweeper.util.UIConstants

/**
 * Values that control the current layout of the BattleSweeper game screen.
 *
 * These are kept here for now rather than being moved into the individual
 * components so the entire screen's layout can be tuned from one place.
 */
private object PlayBattleSweeperScreenDefaults {

    // Header layout
    const val HEADER_HEIGHT_FRACTION = 0.12f
    val HEADER_TOP_PADDING = 23.dp
    val HEADER_BORDER_WIDTH = 8.dp

    // Header control spacing
    val MINES_COUNTER_START_PADDING = 30.dp
    val CONTROL_SPACING = 35.dp

    // Seven-segment display configuration
    const val MINES_COUNTER_DIGITS = 3
    const val MINES_COUNTER_DIGIT_WIDTH = 40
    const val MINES_COUNTER_DIGIT_HEIGHT = 70

    const val TIMER_DIGITS = 2
    const val TIMER_DIGIT_WIDTH = 40
    const val TIMER_DIGIT_HEIGHT = 100

    // Button sizing
    const val RESET_BUTTON_HEIGHT = 40
    const val RESET_BUTTON_WIDTH = 40
    const val FLAG_BUTTON_DIAMETER = 56
    val FLAG_BUTTON_MODIFIER_SIZE = 40.dp

    // Board tile sizing
    const val TILE_WIDTH = 33
    const val TILE_HEIGHT = 33
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "GameScreen"
)
@Composable
fun PlayBattleSweeperScreen(
    viewModel: BattleSweeperViewModel = viewModel()
) {
    val gameState by viewModel.gameState.collectAsState()

    val density = LocalDensity.current

    // BS_Border expects its width in pixels, while the rest of this screen
    // is laid out using dp.
    val borderWidthPx = with(density) {
        PlayBattleSweeperScreenDefaults.HEADER_BORDER_WIDTH.toPx()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(
                        PlayBattleSweeperScreenDefaults.HEADER_HEIGHT_FRACTION
                    )
                    .fillMaxWidth()
                    .padding(
                        top = PlayBattleSweeperScreenDefaults.HEADER_TOP_PADDING
                    )
                    .background(Color.LightGray)
                    .BS_Border(
                        lightGray = UIConstants.BORDERCOLOR_WHITE,
                        medGray = UIConstants.BORDERCOLOR_LIGHTGRAY,
                        darkGray = UIConstants.BORDERCOLOR_GRAY,
                        borderWidth = borderWidthPx
                    )
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(
                            start = PlayBattleSweeperScreenDefaults.MINES_COUNTER_START_PADDING
                        )
                ) {
                    SevenSegmentCounter(
                        number = gameState.minesRemaining,
                        digits = PlayBattleSweeperScreenDefaults.MINES_COUNTER_DIGITS,
                        counterHeight = PlayBattleSweeperScreenDefaults.MINES_COUNTER_DIGIT_WIDTH,
                        counterWidth = PlayBattleSweeperScreenDefaults.MINES_COUNTER_DIGIT_HEIGHT,
                        modifier = Modifier
                    )
                }

                Spacer(
                    modifier = Modifier.width(
                        PlayBattleSweeperScreenDefaults.CONTROL_SPACING
                    )
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    ResetButton(
                        onClick = viewModel::onResetButtonClickHandler,
                        buttonHeight = PlayBattleSweeperScreenDefaults.RESET_BUTTON_HEIGHT,
                        buttonWidth = PlayBattleSweeperScreenDefaults.RESET_BUTTON_WIDTH,
                        modifier = Modifier
                    )
                }

                Spacer(
                    modifier = Modifier.width(
                        PlayBattleSweeperScreenDefaults.CONTROL_SPACING
                    )
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    ToggleFlagButton(
                        flagMode = gameState.flagMode,
                        onClick = viewModel::onFlagModeButtonClickHandler,
                        buttonDiameter = PlayBattleSweeperScreenDefaults.FLAG_BUTTON_DIAMETER,
                        modifier = Modifier.size(
                            PlayBattleSweeperScreenDefaults.FLAG_BUTTON_MODIFIER_SIZE
                        )
                    )
                }

                Spacer(
                    modifier = Modifier.width(
                        PlayBattleSweeperScreenDefaults.CONTROL_SPACING
                    )
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    SevenSegmentTimer(
                        gameState.elapsedSeconds,
                        PlayBattleSweeperScreenDefaults.TIMER_DIGITS,
                        PlayBattleSweeperScreenDefaults.TIMER_DIGIT_WIDTH,
                        PlayBattleSweeperScreenDefaults.TIMER_DIGIT_HEIGHT,
                        Modifier
                    )

                    // SevenSegmentColon(60, 20)
                }
            }

            Box(
                modifier = Modifier
                    .BS_Border(
                        lightGray = UIConstants.BORDERCOLOR_WHITE,
                        medGray = UIConstants.BORDERCOLOR_LIGHTGRAY,
                        darkGray = UIConstants.BORDERCOLOR_GRAY,
                        borderWidth = borderWidthPx
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(
                            PlayBattleSweeperScreenDefaults.HEADER_BORDER_WIDTH
                        )
                ) {
                    TileGrid(
                        board = gameState.board,
                        onTileClick = viewModel::onTileClick,
                        onTileLongClick = viewModel::onTileLongClick,
                        tileWidth = PlayBattleSweeperScreenDefaults.TILE_WIDTH,
                        tileHeight = PlayBattleSweeperScreenDefaults.TILE_HEIGHT
                    )
                }
            }
        }
    }
}
