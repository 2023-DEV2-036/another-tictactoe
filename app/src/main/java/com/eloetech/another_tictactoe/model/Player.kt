package com.eloetech.another_tictactoe.model

import androidx.compose.ui.graphics.Color

/**
 * A Player model for this game.
 */
data class Player(
    val symbol: PlayerSymbol,
    val color: Color
)