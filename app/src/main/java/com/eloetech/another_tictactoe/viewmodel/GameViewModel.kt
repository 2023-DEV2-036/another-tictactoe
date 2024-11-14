package com.eloetech.another_tictactoe.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.eloetech.another_tictactoe.model.GameState

class GameViewModel : ViewModel() {

    private val _state = mutableStateOf(GameState())
    val state: State<GameState> = _state

    fun onCellClicked(row: Int, col: Int) {
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

    fun resetGame() {
        val newState = _state.value.copy()
        newState.resetGame()
        _state.value = newState
    }
}