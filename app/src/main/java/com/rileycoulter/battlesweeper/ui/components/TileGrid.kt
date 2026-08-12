package com.rileycoulter.battlesweeper.ui.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rileycoulter.battlesweeper.game.Position
import com.rileycoulter.battlesweeper.game.TileState


@Composable
fun TileGrid(
    board: List<List<TileState>>,
    modifier: Modifier = Modifier,
    tileHeight: Int = 36,
    tileWidth: Int = 36,
    onTileClick: (position: Position) -> Unit,
    onTileLongClick: (position: Position) -> Unit

) {
    Column (modifier = modifier){
        repeat(board.size) { row ->
            Row {
                repeat(board[row].size) { col ->
                    Tile(
                        tileState = board[row][col],
                        onClick = {
                            onTileClick(Position(row, col))
                        },
                        onLongClick = {
                            onTileLongClick(Position(row, col))
                        },
                        tileHeight = tileHeight,
                        tileWidth = tileWidth,
                    )
                }
            }
        }
    }
}