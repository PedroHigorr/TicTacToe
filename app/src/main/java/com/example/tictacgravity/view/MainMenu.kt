package com.example.tictacgravity.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.tictacgravity.R


@Composable
fun PlayButton(){
    
}

@Composable
fun MenuScreen() {
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.bg_menu),
            contentDescription =  "BackGround Menu",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
            )
    }
}
