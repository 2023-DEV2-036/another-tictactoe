package com.eloetech.another_tictactoe.viewmodel

import com.eloetech.another_tictactoe.model.PlayerSymbol
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test

class GameViewModelTest {
    private lateinit var viewModel: GameViewModel

    @Before
    fun setUp() {
        viewModel = GameViewModel()
    }

    @Test
    fun `initial state is empty`() {
        val initialState = viewModel.state.value
        assertNull(initialState.winner)
        assertFalse(initialState.isDraw)
        initialState.board.flatten().forEach { assertNull(it) }
    }

    @Test
    fun `player X moves first`() {
        viewModel.onCellClicked(0, 0)
        assertEquals("X", viewModel.state.value.board[0][0]?.symbol?.raw)
    }

    @Test
    fun `players alternate turns`() {
        viewModel.onCellClicked(0, 0)
        viewModel.onCellClicked(0, 1)
        assertEquals("X", viewModel.state.value.board[0][0]?.symbol?.raw)
        assertEquals("O", viewModel.state.value.board[0][1]?.symbol?.raw)
    }

    @Test
    fun `detect winning condition`() {
        viewModel.onCellClicked(0, 0) // X
        viewModel.onCellClicked(1, 0) // O
        viewModel.onCellClicked(0, 1) // X
        viewModel.onCellClicked(1, 1) // O
        viewModel.onCellClicked(0, 2) // X wins
        assertEquals("X", viewModel.state.value.winner?.symbol?.raw)
    }

    @Test
    fun `reset game`() {
        viewModel.onCellClicked(0, 0) // X
        viewModel.onCellClicked(1, 0) // O
        viewModel.onCellClicked(0, 1) // X
        viewModel.resetGame()

        var boardContainsPlayer = false
        for (player in viewModel.state.value.board.flatten()) {
            if (player != null) {
                boardContainsPlayer = true
                break
            }
        }
        assertFalse(boardContainsPlayer)
        assertNull(viewModel.state.value.winner)
        assertFalse(viewModel.state.value.isDraw)
        assertEquals(viewModel.state.value.currentPlayer.symbol, PlayerSymbol.X)
    }
}