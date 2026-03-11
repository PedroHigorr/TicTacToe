package com.example.tictacgravity.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.tictacgravity.viewmodel.GameViewModel

    @Composable
    fun GameScreen(viewModel: GameViewModel) {

        val board by viewModel.board.collectAsState()
        val player1Life by viewModel.player1Life.collectAsState()
        val player2Life by viewModel.player2Life.collectAsState()
        val isPlayerTurn by viewModel.isPlayerTurn.collectAsState()
        val isGameOver by viewModel.isGameOver.collectAsState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF906Df))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                PlayerInfo(
                    name = viewModel.player1.name,
                    life = player1Life
                )
                GameBoard(
                    board = board,
                    onCellClick = {line, column -> viewModel.onCellCLick(line, column)}
                )
                PlayerInfo(
                    name = viewModel.player2.name,
                    life = player2Life
                )
            }
        }
    }

    @Composable
    fun PlayerInfo(name: String, life: Int){
        Text(
            "${name}: ${life}"
        )
    }

    @Composable
    fun GameBoard( board: List<List<String>>, onCellClick: (Int, Int) -> Unit){
        Column {
            board.forEachIndexed { i, row ->
                Row {
                    row.forEachIndexed { j, cell ->
                        Button(onClick = { onCellClick(i, j)}) {
                            Text(cell)
                        }
                    }
                }
            }
        }
    }