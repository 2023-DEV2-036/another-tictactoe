package com.eloetech.another_tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.eloetech.another_tictactoe.ui.screen.BoardScreen
import com.eloetech.another_tictactoe.ui.theme.AnothertictactoeTheme
import com.eloetech.another_tictactoe.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnothertictactoeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BoardScreen(viewModel = viewModel)
                }
            }
        }
    }
}