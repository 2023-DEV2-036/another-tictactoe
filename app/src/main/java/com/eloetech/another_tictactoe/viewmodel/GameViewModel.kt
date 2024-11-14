package com.eloetech.another_tictactoe.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.eloetech.another_tictactoe.model.GameState

/**
 * The game view model that provides business logic and data to the *BoardScreen*
 */
class GameViewModel : ViewModel() {

    private val _state = mutableStateOf(GameState())
    val state: State<GameState> = _state

    /**
     * Action on cell selection by the user.
     * Copies the current state and updates its values before replacing the current one.
     *
     * @param row The selected row in the board
     * @param col The selected column in the board
     */
    fun onCellSelected(row: Int, col: Int) {
        val newState = _state.value.copy(
            board = _state.value.board,
            playerX = _state.value.playerX,
            playerO = _state.value.playerO,
            currentPlayer = _state.value.currentPlayer,
            winner = _state.value.winner,
            isDraw = _state.value.isDraw
        )
        newState.makeMove(row, col)
        _state.value = newState
    }

    /**
     * Resets the game.
     * Removes all previous moves from any player and restores the state to its initial values.
     */
    fun resetGame() {
        val newState = _state.value.copy()
        newState.resetGame()
        _state.value = newState
    }
}