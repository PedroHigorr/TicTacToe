package com.example.tictacgravity.model

class MainMatrix {

    private var count = 1;

    private val board = Array(3){ Array(3){""} }
    private val chooseBoard = Array(3){ IntArray(3){count++} }

    fun showBoard(){

        for(i in board.indices){
            for(j in board.indices){
                print("\t| ${board[i][j]} |");
            }
            println()
        }
    }

    //Remover
    fun displayChoices(){
        for(i in chooseBoard.indices){
            for(j in chooseBoard.indices){
                print("\t${chooseBoard[i][j]}");
            }
            println()
        }
    }

    fun alterBoard(index: Int, player: Player): Boolean {

        for(i in chooseBoard.indices){
            for(j in chooseBoard.indices){
                if(chooseBoard[i][j] == index){

                    return att(player.playerSymbol, i, j )

                }
            }
        }
        return false
    }

    private fun att(playerSymbol: String, line: Int, column: Int): Boolean {

        return if(!hasASymbol(line, column)) {
            board[line][column] = playerSymbol
            true
        } else false

    }

    private fun hasASymbol(line: Int, column: Int): Boolean{

        val hasASymbol = board[line][column].isNotEmpty()

        return hasASymbol
    }

    fun hasAnyTriad(symbol: String): List<Pair<Int, Int>> {

        for( i in board.indices){

            val horizontalTriadSuccess = checkHorizontalTriads(symbol, i)

            if(horizontalTriadSuccess.isNotEmpty()){
                return horizontalTriadSuccess;
            }

            val verticalTriadSuccess = checkVerticalTriads(symbol, i)

            if(verticalTriadSuccess.isNotEmpty()){
                return verticalTriadSuccess
            }

        }

        val diagonalTriadSuccess = checkDiagonalTriads(symbol)

        if(diagonalTriadSuccess.isNotEmpty()){
            return diagonalTriadSuccess
        }

        return emptyList()

    }

    private fun checkHorizontalTriads(symbol: String, line: Int): List<Pair<Int,Int>> {

        val lineTriads = mutableListOf<Pair<Int, Int>>()

        if(board[line][0] == symbol && board[line][0] == board[line][1] && board[line][1] == board[line][2]){

            lineTriads.add(Pair(line, 0))
            lineTriads.add(Pair(line, 1))
            lineTriads.add(Pair(line,2))
        }

        return lineTriads
    }

    private fun checkVerticalTriads(symbol: String, column: Int): List<Pair<Int,Int>>{

        val verticalTriads = mutableListOf<Pair<Int, Int>>()

        if(board[0][column] == symbol && board[0][column] == board[1][column] && board[1][column] == board[2][column]){

            verticalTriads.add(Pair(0, column))
            verticalTriads.add(Pair(1, column))
            verticalTriads.add(Pair(2, column))

        }

        return verticalTriads
    }

    private fun checkDiagonalTriads(symbol: String, ): List<Pair<Int,Int>>{

        val diagonalTriads = mutableListOf<Pair<Int, Int>>()

        if(board[0][0] == symbol && board[0][0] == board[1][1] && board[1][1] == board[2][2]){

            diagonalTriads.add (Pair(0, 0))
            diagonalTriads.add (Pair(1, 1))
            diagonalTriads.add (Pair(2, 2))

        }

        if(board[0][2] == symbol && board[0][2] == board[1][1] && board[1][1] == board[2][0]){

            diagonalTriads.add (Pair(0, 2))
            diagonalTriads.add (Pair(1, 1))
            diagonalTriads.add (Pair(2, 0))

        }

        return diagonalTriads
    }

    fun removeTriads(pairs: List<Pair<Int, Int>>){

        for(pair in pairs){
            board[pair.first][pair.second] = ""
        }
    }

    fun isBoardFull(): Boolean {

        return board.all { row -> row.all {it.isNotEmpty()}}

    }

    fun clearBoard(){

        for(row in board){
            for(column in row.indices){
                row[column] = ""
            }
        }
    }
}