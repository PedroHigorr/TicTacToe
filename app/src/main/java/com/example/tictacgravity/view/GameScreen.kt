package com.example.tictacgravity.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.tictacgravity.viewmodel.GameViewModel

    @Composable
    fun GameScreen(viewModel: GameViewModel) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF906Df))
        )
    }