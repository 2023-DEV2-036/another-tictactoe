package com.eloetech.another_tictactoe.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eloetech.another_tictactoe.R
import com.eloetech.another_tictactoe.viewmodel.GameViewModel

@Composable
fun BoardScreen(viewModel: GameViewModel) {
    val state by viewModel.state
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.tic_tac_toe), style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(stringResource(R.string.turn_to_play).replace("{player}", state.currentPlayer.symbol.raw))
        Spacer(Modifier.height(8.dp))
        state.board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, player ->
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .border(1.dp, Color.Black)
                            .clickable { viewModel.onCellSelected(rowIndex, colIndex) }
                            .testTag("cell_${rowIndex}_$colIndex"),
                        contentAlignment = Alignment.Center
                    ) {
                        player?.let {
                            Text(
                                text = it.symbol.raw,
                                fontWeight = FontWeight.Bold,
                                color = it.color
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.resetGame() }) {
            Text(stringResource(R.string.restart))
        }
        Spacer(Modifier.height(8.dp))
        state.winner?.let {
            Text("${stringResource(R.string.winner)}: ${it.symbol.raw}")
        } ?: if (state.isDraw) {
            Text(stringResource(R.string.draw))
        } else {
            Text(stringResource(R.string.suspense))
        }
    }
}