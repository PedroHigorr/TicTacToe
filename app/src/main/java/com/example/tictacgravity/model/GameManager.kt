package com.example.tictacgravity.model

class GameManager {

    var isPlayerTurn: Boolean = true


    fun switchTurn() {
        isPlayerTurn = !isPlayerTurn
    }

    fun damage(player: Player, damage: Int) {

        //player.life -= damage
    }
}