package com.example.mouse_netology.screens

import MainMenu
import StatisticsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mouse_netology.ui.theme.MouseNetologyTheme
import com.example.netology.presentation.Game

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MouseNetologyTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MouseNetologyTheme {
        Greeting("Android")
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    val objectCount = remember { mutableStateOf(2) } // Начальное значение
    val speed = remember { mutableStateOf(1f) } // Начальное значение
    NavHost(navController, startDestination = "splash") {
        composable("splash") { Splash { navController.navigate("mainMenu") } }
        composable("mainMenu"){

            MainMenu(
                onPlayClick = {
                    navController.navigate("game");
                },
                onStatisticsClick = { navController.navigate("statistics") },
                objectCount = objectCount.value,
                onObjectCountChange = { count -> objectCount.value = count },
                speed = speed.value,
                onSpeedChange = { newSpeed -> speed.value = newSpeed }
            )
        }
        composable("statistics") { StatisticsScreen() }
        composable("game") { Game(objectCount.value, speed.value, 100f,navController) }
    }
}