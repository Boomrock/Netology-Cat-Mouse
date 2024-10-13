import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mouse_netology.ui.theme.MouseNetologyTheme

@Composable
fun MainMenu(
    onPlayClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    objectCount: Int,
    onObjectCountChange: (Int) -> Unit,
    speed: Float,
    onSpeedChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Главное меню", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onPlayClick) {
            Text("Играть")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onStatisticsClick) {
            Text("Статистика")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Настройка количества игровых объектов
        Text("Количество игровых объектов: $objectCount")
        Row {
            Button(onClick = { if (objectCount > 1) onObjectCountChange(objectCount - 1) }) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onObjectCountChange(objectCount + 1) }) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Настройка скорости передвижения
        Text("Скорость передвижения: ${speed}x")
        Row {
            Button(onClick = { if (speed > 0.1f) onSpeedChange(speed - 0.1f) }) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onSpeedChange(speed + 0.1f) }) {
                Text("+")
            }
        }
    }
}

@Preview()
@Composable()
fun MenuPreview(){
    MouseNetologyTheme {
        val objectCount = remember { mutableStateOf(2) } // Начальное значение
        val speed = remember { mutableStateOf(1f) } // Начальное значение
        MainMenu(
            onPlayClick = { /* Логика для начала игры */ },
            onStatisticsClick = { /* Логика для отображения статистики */ },
            objectCount = objectCount.value,
            onObjectCountChange = { count -> objectCount.value = count },
            speed = speed.value,
            onSpeedChange = { newSpeed -> speed.value = newSpeed }
        )    }
}