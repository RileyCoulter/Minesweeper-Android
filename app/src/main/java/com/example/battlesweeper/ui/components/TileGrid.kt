package com.example.battlesweeper.ui.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.battlesweeper.GameState.Position
import com.example.battlesweeper.GameState.TileState


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