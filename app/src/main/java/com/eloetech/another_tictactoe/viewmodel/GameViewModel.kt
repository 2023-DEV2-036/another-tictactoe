package com.eloetech.another_tictactoe.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.eloetech.another_tictactoe.model.GameState

class GameViewModel : ViewModel() {
    val state = mutableStateOf(GameState())

    fun onCellClicked(row: Int, col: Int) {
        state.value.makeMove(row, col)
    }
}