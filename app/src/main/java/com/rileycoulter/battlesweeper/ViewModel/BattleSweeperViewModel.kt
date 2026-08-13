package com.rileycoulter.battlesweeper.ViewModel

import android.util.Log
import androidx.collection.emptyLongSet
import androidx.lifecycle.ViewModel
import com.rileycoulter.battlesweeper.game.BattleSweeperGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.System
import androidx.lifecycle.viewModelScope
import com.rileycoulter.battlesweeper.game.BattleSweeperGame.calcMinesRemaining
import com.rileycoulter.battlesweeper.game.BattleSweeperGame.checkForWin
import com.rileycoulter.battlesweeper.game.BattleSweeperGame.flagTile
import com.rileycoulter.battlesweeper.game.BattleSweeperGame.revealAdjacentTiles
import com.rileycoulter.battlesweeper.game.BattleSweeper_GameSettings
import com.rileycoulter.battlesweeper.game.BattleSweeper_GameSettings_Presets
import com.rileycoulter.battlesweeper.game.GameState
import com.rileycoulter.battlesweeper.game.GameStatus
import com.rileycoulter.battlesweeper.game.Position
import com.rileycoulter.battlesweeper.game.TileState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class BattleSweeperViewModel : ViewModel() {

    private val _gameState: MutableStateFlow<GameState> = MutableStateFlow(
        GameState(
            board = List(BattleSweeper_GameSettings_Presets.Beginner.rows) {
                List(BattleSweeper_GameSettings_Presets.Beginner.columns) {
                    TileState(
                        isMine = false,
                        isRevealed = false,
                        isFlagged = false,
                        adjacentMines = 0
                    )
                }
            },
            minesRemaining = 0,
            elapsedSeconds = 0,
            flagMode = false,
            gameStatus = GameStatus.NotStarted,
            startTime = null,
            gameSettings = BattleSweeper_GameSettings_Presets.Beginner,
        )
    )

    val gameState: StateFlow<GameState> = _gameState

    private var timerJob: Job? = null


    fun setGameSettings(settings: BattleSweeper_GameSettings) {
        var state = _gameState.value.copy(gameSettings = settings)
        if (_gameState.value.gameStatus == GameStatus.NotStarted ){
            _gameState.value = state
        }
    }

    fun startNewGame(settings: BattleSweeper_GameSettings, startingPosition: Position) {
        _gameState.value =    _gameState.value.copy(
                    board = BattleSweeperGame.initializeBoard(settings, startingPosition),
                    minesRemaining = settings.mines,
                    elapsedSeconds = 0,
                    gameStatus = GameStatus.Playing,
                    startTime = System.nanoTime(),
                    gameSettings = settings,
                    flagMode = true
        )
        startTimer()

    }

    fun onTileClick(position: Position) {
        val tileState = _gameState.value.board[position.row][position.col]
        Log.d("RileyBattlesweeper", "Tile was clicked at row = ${position.row} and col = ${position.col}!, adjacentMines = ${tileState.adjacentMines}")

        val state = _gameState.value
        var newBoard: List<List<TileState>> = state.board
        var newMinesRemaining: Int = state.minesRemaining

        // Handle clicks if the game isn't currently being played
        if (state.gameStatus == GameStatus.NotStarted) {
            startNewGame(settings = state.gameSettings, startingPosition = position)
            return
        }

        // Already revealed tiles -> reveal adjacent unflagged tiles, if adjacent flags > adjacent mines
        if (state.board[position.row][position.col].isRevealed) {
            newBoard = revealAdjacentTiles(
                position = position,
                board = state.board
            )
        }
        // Unrevealed tile + in flag mode -> flag the tile + update minecount accordingly
        else if (!state.board[position.row][position.col].isRevealed && state.flagMode) {
            newBoard = flagTile(position = position, board = state.board)
            newMinesRemaining = calcMinesRemaining(newBoard)
        }
        // Unrevealed tile + not in flag mode -> Reveal the tile
        else {
            newBoard = BattleSweeperGame.revealTile(
                board = state.board,
                position = position
            )
        }

        // Update GameState
        newBoard.let {
            _gameState.value = state.copy(
                board = it,
                minesRemaining = newMinesRemaining
            )
        }

        // Check whether the player has won
        checkForWin()

    }

    fun onTileLongClick(position: Position) {
        Log.d("RileyBattlesweeper", "Tile was long clicked! row = ${position.row} and col = ${position.col}!,")
        
    }

    fun onResetButtonClickHandler() {
        Log.d("RileyBattlesweeper", "Reset Button was clicked!")

    }

    fun onFlagModeButtonClickHandler() {
        Log.d("RileyBattlesweeper", "Flag Mode Button was clicked!")

    }

    //coroutines
    private fun startTimer() {
        timerJob?.cancel()

        timerJob = viewModelScope.launch {
            while (true) {
                delay(50)

                val startTime = _gameState.value.startTime ?: continue

                val elapsedSeconds = ((System.nanoTime() - startTime) / 1_000_000_000.0).roundToInt()

                _gameState.value = _gameState.value.copy(
                    elapsedSeconds = elapsedSeconds,
                )
            }
        }
    }

}










