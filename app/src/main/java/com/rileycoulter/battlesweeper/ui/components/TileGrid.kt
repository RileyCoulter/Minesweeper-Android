package com.rileycoulter.battlesweeper.ui.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rileycoulter.battlesweeper.game.Position
import com.rileycoulter.battlesweeper.game.TileState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun TileGrid(
    board: List<List<TileState>>,
    modifier: Modifier = Modifier,
    tileHeight: Int = 36,
    tileWidth: Int = 36,
    onTileClick: (position: Position) -> Unit,
    onTileLongClick: (position: Position) -> Unit

) {
    var pressedPosition by remember { mutableStateOf<Position?>(null) }

    fun isAdjacent(position1: Position, position2: Position): Boolean {
        val rowDifference = kotlin.math.abs(position1.row - position2.row)
        val colDifference = kotlin.math.abs(position1.col - position2.col)

        return rowDifference <= 1 &&
                colDifference <= 1 &&
                position1 != position2
    }

    Column (modifier = modifier){
        repeat(board.size) { row ->
            Row {
                repeat(board[row].size) { col ->
                    val position = Position(row, col)
                    val currentPressedPosition = pressedPosition
                    Tile(
                        tileState = board[row][col],
                        onClick = {
                            onTileClick(position)
                        },
                        onLongClick = {
                            onTileLongClick(position)
                        },
                        tileHeight = tileHeight,
                        tileWidth = tileWidth,
                        isPressed =
                            position == currentPressedPosition ||
                                    (
                                            currentPressedPosition != null &&
                                            board[currentPressedPosition.row][currentPressedPosition.col].isRevealed &&
                                            isAdjacent(position, currentPressedPosition)
                                    ),
                        onPressedChanged = { pressed ->
                            pressedPosition = if (pressed) {
                                position
                            } else {
                                null
                            }
                        }
                    )
                }
            }
        }
    }
}