package com.eloetech.another_tictactoe.model

import com.eloetech.another_tictactoe.factory.PlayerFactory

data class GameState(
    var board: Array<Array<Player?>> = Array(3) { arrayOfNulls<Player>(3) },
    val playerX: Player = PlayerFactory.createPlayer(PlayerSymbol.X),
    val playerO: Player = PlayerFactory.createPlayer(PlayerSymbol.O),
    var currentPlayer: Player = playerX,
    var winner: Player? = null,
    var isDraw: Boolean = false
) {

    fun makeMove(row: Int, col: Int, player: Player? = null) {
        if (board[row][col] != null || winner != null) return
        val newBoard = board.map { it.clone() }.toTypedArray()
        newBoard[row][col] = player ?: currentPlayer
        board = newBoard
        currentPlayer = nextPlayer()
        winner = checkWinner()
        isDraw = checkDraw()
    }

    private fun nextPlayer(): Player {
        return when (currentPlayer.symbol) {
            PlayerSymbol.X -> playerO
            PlayerSymbol.O -> playerX
        }
    }

    private fun checkWinner(): Player? {
        // Check rows for a win
        for (row in board) {
            if (row[0] != null && row[0] == row[1] && row[1] == row[2]) {
                return row[0] // Return the winning mark ("X" or "O")
            }
        }

        // Check columns for a win
        for (col in 0..2) {
            if (board[0][col] != null && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return board[0][col] // Return the winning mark
            }
        }

        // Check the first diagonal for a win
        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0] // Return the winning mark
        }

        // Check the second diagonal for a win
        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2] // Return the winning mark
        }

        // No winner
        return null
    }

    private fun checkDraw(): Boolean {
        return board.flatten().all { it != null } && winner == null
    }

    fun resetGame() {
        board = Array(3) { arrayOfNulls<Player>(3) }
        currentPlayer = playerX
        winner = null
        isDraw = false
    }
}