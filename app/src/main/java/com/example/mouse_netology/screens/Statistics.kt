import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun StatisticsScreen() {
    val statistics = remember { mutableStateOf(listOf<GameStatistics>()) }
    val context = LocalContext.current;
    // Загружаем данные из базы данных
    LaunchedEffect(Unit) {
        val databaseHelper = DatabaseHelper(context);
        statistics.value = databaseHelper.getAllGameStatistics()
        Log.d("db", statistics.value[0].toString())
        Log.d("launch", "asda")

    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (statistics.value.isEmpty()) {
                Text("No statistics available", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            } else {
                LazyColumn {
                    items(statistics.value.takeLast(10).reversed()) { stat ->
                        StatisticItem(stat)
                    }
                }
            }
        }
    }
}

@Composable
fun StatisticItem(stat: GameStatistics) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Попаданий: ${stat.mouseClicks}")
            Text("Всего кликов: ${stat.totalClicks}")
            Text("Процент попаданий: ${stat.hitPercentage}")
            Text("Продолжительность игры: ${(stat.gameDuration/1000).toInt()} секунд")
        }
    }
}

