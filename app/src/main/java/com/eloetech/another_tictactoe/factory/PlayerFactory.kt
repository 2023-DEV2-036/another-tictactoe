package com.eloetech.another_tictactoe.factory

import androidx.compose.ui.graphics.Color
import com.eloetech.another_tictactoe.model.Player
import com.eloetech.another_tictactoe.model.PlayerSymbol

object PlayerFactory {
    fun createPlayer(symbol: PlayerSymbol): Player {
        val color: Color = when (symbol) {
            PlayerSymbol.X -> Color.Blue
            PlayerSymbol.O -> Color.Red
        }

        return Player(symbol, color)
    }

    fun nextPlayer(currentPlayer: Player): Player {
        val nextPlayerSymbol: PlayerSymbol = when (currentPlayer.symbol) {
            PlayerSymbol.O -> PlayerSymbol.X
            PlayerSymbol.X -> PlayerSymbol.O
        }
        return createPlayer(nextPlayerSymbol)
    }
}