package com.example.tictacgravity.viewmodel

import com.example.tictacgravity.model.Player

class GameViewModel {

    var isPlayerTurn: Boolean = true


    fun switchTurn(){
        isPlayerTurn = !isPlayerTurn
    }

    fun damage(player: Player, damage: Int){

        player.life -= damage
    }

}