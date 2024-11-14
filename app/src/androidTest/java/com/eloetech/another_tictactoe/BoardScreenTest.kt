package com.eloetech.another_tictactoe

// Import necessary testing libraries
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.eloetech.another_tictactoe.ui.screen.BoardScreen
import com.eloetech.another_tictactoe.viewmodel.GameViewModel
import org.junit.Rule
import org.junit.Test

class BoardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCellClickUpdatesBoard() {
        // Set the content to BoardScreen
        composeTestRule.setContent {
            BoardScreen(GameViewModel())
        }

        // Click on the cell at (0, 0)
        composeTestRule.onNodeWithTag("cell_0_0").performClick()

        // Verify that the cell now contains an 'X' or the current player's symbol
        composeTestRule.onNodeWithTag("cell_0_0").assertTextEquals("X")
    }
}