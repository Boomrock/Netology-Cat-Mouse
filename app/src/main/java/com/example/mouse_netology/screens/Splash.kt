package com.example.mouse_netology.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mouse_netology.R
import com.example.mouse_netology.ui.theme.MouseNetologyTheme
import kotlinx.coroutines.delay

@Composable
fun Splash (onTimeout: () -> Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Кошки мышки")
    }

    // Задержка перед переходом на основной экран
    LaunchedEffect(Unit) {
        delay(2000) // Задержка в 2 секунды
        onTimeout()
    }
}
@Preview()
@Composable
fun SplashPreview(){
    MouseNetologyTheme{
        Splash(){

        }
    }

}