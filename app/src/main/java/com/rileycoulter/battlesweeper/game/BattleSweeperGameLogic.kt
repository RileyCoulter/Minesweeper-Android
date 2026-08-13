package com.rileycoulter.battlesweeper.game

import android.util.Log


object BattleSweeperGame {

    private fun generateMinePositions(
        settings: BattleSweeper_GameSettings,
        forbiddenPositions: Set<Position>
    ): List<Position> {

        val positions = mutableListOf<Position>()

        for (row in 0 until settings.rows) {
            for (col in 0 until settings.columns) {
                val position = Position(row, col)

                if (position !in forbiddenPositions) {
                    positions.add(position)
                }
            }
        }

        positions.shuffle()

        return positions.take(settings.mines)
    }

    private fun generateZeroTiles(
        start: Position,
        rows: Int,
        columns: Int,
        count: Int
    ): Set<Position> {
        val zeroTiles = mutableSetOf(start)

        while (zeroTiles.size < count) {

            val possibleExpansions = zeroTiles
                .flatMap { mutableListOf(
                    Position(it.row-1,it.col),
                    Position(it.row,it.col-1),
                    Position(it.row,it.col+1),
                    Position(it.row+1,it.col),
                    )
                }
                .filter { it !in zeroTiles }
                .filter { 0 <= it.row && it.row < rows}
                .filter { 0 <= it.col && it.col < columns}
                .distinct()

            if (possibleExpansions.isEmpty()) {
                break
            }

            zeroTiles.add(
                possibleExpansions.random()
            )
        }
        val edges = zeroTiles
            .flatMap { mutableListOf(
                Position(it.row-1,it.col-1),
                Position(it.row-1,it.col),
                Position(it.row-1,it.col+1),
                Position(it.row,it.col-1),
                Position(it.row,it.col),
                Position(it.row,it.col+1),
                Position(it.row+1,it.col-1),
                Position(it.row+1,it.col),
                Position(it.row+1,it.col+1),
            )
            }
            .filter { it !in zeroTiles }
            .filter { 0 <= it.row && it.row < rows}
            .filter { 0 <= it.col && it.col < columns}
            .distinct()

        zeroTiles.addAll(edges)
        return zeroTiles.toSet()
    }

    fun initializeBoard(
        settings: BattleSweeper_GameSettings,
        startingPosition: Position
    ): List<List<TileState>> {

        // 1. Generate mine positions
        val minePositions: List<Position> = generateMinePositions(settings, generateZeroTiles(startingPosition, settings.rows, settings.columns, settings.guaranteedStartingSize))

        // 2. Build Board
        val tempBoard = mutableListOf<MutableList<TileState>>()
        for (row in 0 until settings.rows) {
            tempBoard.add(mutableListOf<TileState>())
            for (col in 0 until settings.columns) {
                val currPosition: Position = Position(row, col)
                val adjacentMines: Int = minePositions.count {
                    kotlin.math.abs(it.row - row) <= 1 &&
                            kotlin.math.abs(it.col - col) <= 1 &&
                            !(it.row == row && it.col == col)
                }
                tempBoard[row].add(TileState(
                    isMine = currPosition in minePositions,
                    isRevealed = false,
                    isFlagged = false,
                    adjacentMines = adjacentMines))
            }
        }

        // 3. Return board
        return tempBoard.map { it.toList() }
    }

    //attempt to reveal the given tile only if it is not flagged.
    fun revealTile(position: Position, board: List<List<TileState>>): List<List<TileState>>  {
        Log.d("RileyBattlesweeper", "Tile was revealed! row = ${position.row} and col = ${position.col}!,")



        return  mutableListOf<MutableList<TileState>>().toList()
    }

    //Attempt to reveal all 8 tiles surrounding the given tile only if the number of flags in
    //the surrounding tiles is greater than or equal to the given tile's adjacent mine count.
    fun revealAdjacentTiles(position: Position, board: List<List<TileState>>): List<List<TileState>>  {
        Log.d("RileyBattlesweeper", "Adjacent tiles were revealed row = ${position.row} and col = ${position.col}!,")
        return mutableListOf<MutableList<TileState>>().toList()
    }

    //attempt to place a flag on the tile. If tile is already flagged, remove it.
    //mine count updated separately
    fun flagTile(position: Position, board: List<List<TileState>>): List<List<TileState>> {
        Log.d("RileyBattlesweeper", "Tile was flagged! row = ${position.row} and col = ${position.col}!,")
        var newBoard = mutableListOf<MutableList<TileState>>()
        for (row in 0 until board.size) {
            newBoard.add(mutableListOf<TileState>())
            for (col in 0 until board[row].size) {
                if (row == position.row && col == position.col) {
                    newBoard[row].add(board[row][col].copy(isFlagged = !board[row][col].isFlagged))
                }
                else {
                    newBoard[row].add(board[row][col].copy())
                }

            }
        }
        return newBoard.toList()

    }

    fun calcMinesRemaining(board: List<List<TileState>>): Int {
        var currMinesOnBoard: Int = 0
        var currFlagsOnBoard: Int = 0

        for (row in 0 until board.size) {
            for (col in 0 until board[row].size) {
                if (board[row][col].isMine) {
                    currMinesOnBoard += 1
                }
                if (board[row][col].isFlagged) {
                    currFlagsOnBoard += 1
                }
            }
        }
        return Math.max(currMinesOnBoard - currFlagsOnBoard, 0)

    }

    fun checkForWin() {
        Log.d("RileyBattlesweeper", "checking for a win!")

    }

    fun endGame() {
        Log.d("RileyBattlesweeper", "ending the game!")

    }
}