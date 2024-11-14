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
}