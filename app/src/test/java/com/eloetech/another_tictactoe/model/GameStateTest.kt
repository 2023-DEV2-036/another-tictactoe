package com.eloetech.another_tictactoe.model

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GameStateTest {

    private lateinit var gameState: GameState

    @Before
    fun setUp() {
        gameState = GameState()
    }

    @Test
    fun `initial game state should be empty`() {
        val board = gameState.board
        for (row in board) {
            for (cell in row) {
                assertNull(cell)
            }
        }
        assertNull(gameState.winner)
        assertTrue { !gameState.isDraw }
    }

    @Test
    fun `making a move updates the board correctly`() {
        gameState.makeMove(0, 0, gameState.playerX)
        assertEquals("X", gameState.board[0][0]?.symbol?.raw)
    }

    @Test
    fun `making a move on an occupied spot does not change the board`() {
        gameState.makeMove(0, 0, gameState.playerX)
        gameState.makeMove(0, 0, gameState.playerO)
        assertEquals("X", gameState.board[0][0]?.symbol?.raw)  // Should remain "X"
    }

    @Test
    fun `game detects a win correctly for a row`() {
        gameState.makeMove(0, 0, gameState.playerX)
        gameState.makeMove(0, 1, gameState.playerX)
        gameState.makeMove(0, 2, gameState.playerX)
        assertEquals("X", gameState.winner?.symbol?.raw)
    }

    @Test
    fun `game detects a win correctly for a column`() {
        gameState.makeMove(0, 0, gameState.playerO)
        gameState.makeMove(1, 0, gameState.playerO)
        gameState.makeMove(2, 0, gameState.playerO)
        assertEquals("O", gameState.winner?.symbol?.raw)
    }

    @Test
    fun `game detects a win correctly for a diagonal`() {
        gameState.makeMove(0, 0, gameState.playerX)
        gameState.makeMove(1, 1, gameState.playerX)
        gameState.makeMove(2, 2, gameState.playerX)
        assertEquals("X", gameState.winner?.symbol?.raw)
    }

    @Test
    fun `game detects a draw correctly`() {
        gameState.makeMove(0, 0, gameState.playerX)
        gameState.makeMove(0, 1, gameState.playerO)
        gameState.makeMove(0, 2, gameState.playerX)
        gameState.makeMove(1, 0, gameState.playerX)
        gameState.makeMove(1, 1, gameState.playerO)
        gameState.makeMove(1, 2, gameState.playerX)
        gameState.makeMove(2, 0, gameState.playerO)
        gameState.makeMove(2, 1, gameState.playerX)
        gameState.makeMove(2, 2, gameState.playerO)

        assertTrue { gameState.isDraw }
        assertNull(gameState.winner)
    }

    @Test
    fun `resetting the game clears the board and state`() {
        gameState.makeMove(0, 0, gameState.playerX)
        gameState.resetGame()

        for (row in gameState.board) {
            for (cell in row) {
                assertNull(cell)
            }
        }
        assertNull(gameState.winner)
        assertTrue { !gameState.isDraw }
    }

    @Test
    fun `making a move goes to next player's turn`() {
        assertEquals(gameState.currentPlayer, gameState.playerX)
        gameState.makeMove(0, 0)
        assertEquals(gameState.currentPlayer, gameState.playerO)
        gameState.makeMove(0, 1)
        assertEquals(gameState.currentPlayer, gameState.playerX)
    }
}