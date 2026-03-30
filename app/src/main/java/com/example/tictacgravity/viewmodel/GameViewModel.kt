package com.example.tictacgravity.viewmodel

import com.example.tictacgravity.model.GameManager
import com.example.tictacgravity.model.MainMatrix
import com.example.tictacgravity.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class GameViewModel {
     //            MutableStateFlow's            \\
    // ------------------ init ------------------ \\
    private  val _player1Life = MutableStateFlow(1000)
    val player1Life: StateFlow<Int> = _player1Life

    private val _player2Life = MutableStateFlow(1000)
    val player2Life: StateFlow<Int> = _player2Life

    private val _isPlayerTurn = MutableStateFlow(true)
    val isPlayerTurn: StateFlow<Boolean> = _isPlayerTurn

    private val _isGameOver = MutableStateFlow(false)
    val isGameOver: StateFlow<Boolean> = _isGameOver

    private val _board = MutableStateFlow(List(3){ List(3) {""} })
    val board: StateFlow<List<List<String>>> = _board

    // ------------------ End ------------------ \\

    val gameManager = GameManager()

    val matrix = MainMatrix()
    val player1 = Player()
    val player2 = Player()


    init {
        _board.value = matrix.getBoard()
        player1.playerSymbol = "X"
        player2.playerSymbol = "O"

        player1.name = "Pedro"
        player2.name = "Bot"
    }

    fun onCellCLick(line: Int, column: Int) {

        val currentPlayer = if (gameManager.isPlayerTurn) player1 else player2
        val success = matrix.alterBoard(line, column, currentPlayer)

        if (success) {

                var triadSuccess = matrix.hasAnyTriad(currentPlayer.playerSymbol)

                while (triadSuccess.isNotEmpty()) {

                    matrix.removeTriads(triadSuccess)

                    if (currentPlayer == player1) {

                        //gameManager.damage(player2, 200)
                        _player2Life.value -= 200

                    } else {

                        //gameManager.damage(player2, 200)
                        _player1Life.value -= 200
                    }

                    if (_player1Life.value <= 0 || _player2Life.value <= 0) {
                        _isGameOver.value = true
                    }

                    matrix.applyGravity(triadSuccess)

                    triadSuccess = matrix.hasAnyTriad(player1.playerSymbol)
                        .ifEmpty { matrix.hasAnyTriad(player2.playerSymbol) }
                }


                if (matrix.isBoardFull()) {

                    matrix.clearBoard()

                }

                _board.value = matrix.getBoard()
                gameManager.switchTurn()

            }

    }

    fun resetGame(){
        _player1Life.value = 1000
        _player2Life.value = 1000
        _isGameOver.value = false
        matrix.clearBoard()
        _isPlayerTurn.value = true
        _board.value = matrix.getBoard()

    }
}