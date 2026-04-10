package com.example.tictacgravity.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tictacgravity.R
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
            .fillMaxSize(),
            contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_board),
            contentDescription =  "BackGround Menu",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        if (isGameOver) {

            Text("Game Over")
            viewModel.resetGame()

        } else {
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
                    onCellClick = { line, column -> viewModel.onCellCLick(line, column) }
                )
                PlayerInfo(
                    name = viewModel.player2.name,
                    life = player2Life
                )
            }
        }
    }
}

@Composable
fun PlayerInfo(name: String, life: Int) {
    Text(
        "${name}: ${life}"
    )
}

@Composable
fun GameBoard(
    board: List<List<String>>,
    onCellClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Usamos Column e Row para criar a grade
    Column(
        modifier = modifier.wrapContentSize(), // Ajusta o tamanho ao conteúdo
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espaço entre as linhas
    ) {
        board.forEachIndexed { i, row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaço entre as colunas
            ) {
                row.forEachIndexed { j, cell ->

                    Box(
                        modifier = Modifier
                            .size(100.dp) // Defina um tamanho fixo para o bloco
                            .clickable { onCellClick(i, j) }, // Clique na célula
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bloco1),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )


                        if (cell.isNotEmpty()) {
                            Image(
                                painter = painterResource(
                                    id = if (cell == "X") R.drawable.x2 else R.drawable.triangle
                                ),
                                contentDescription = cell,
                                modifier = Modifier.size(80.dp), // Símbolo um pouco menor que o bloco
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
            }
        }
    }
}