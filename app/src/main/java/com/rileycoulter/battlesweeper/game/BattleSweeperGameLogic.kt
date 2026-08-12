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
                    isRevealed = true,
                    isFlagged = false,
                    adjacentMines = adjacentMines))
            }
        }

        // 3. Return board
        return tempBoard.map { it.toList() }
    }

    fun revealTile(position: Position, board: List<List<TileState>>): List<List<TileState>>  {
        Log.d("RileyBattlesweeper", "Tile was revealed! row = ${position.row} and col = ${position.col}!,")
        return  mutableListOf<MutableList<TileState>>().toList()
    }

    fun revealAdjacentTiles(position: Position, board: List<List<TileState>>): List<List<TileState>>  {
        Log.d("RileyBattlesweeper", "Adjacent tiles were revealed row = ${position.row} and col = ${position.col}!,")
        return mutableListOf<MutableList<TileState>>().toList()
    }

    fun checkForWin() {
        Log.d("RileyBattlesweeper", "checking for a win!")

    }

    fun endGame() {
        Log.d("RileyBattlesweeper", "ending the game!")

    }
}