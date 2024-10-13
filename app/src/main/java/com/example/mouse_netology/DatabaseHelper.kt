import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun insertGameStatistics(totalClicks: Int, mouseClicks: Int, hitPercentage: Float, gameDuration: Float): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TOTAL_CLICKS, totalClicks)
            put(COLUMN_MOUSE_CLICKS, mouseClicks)
            put(COLUMN_HIT_PERCENTAGE, hitPercentage)
            put(COLUMN_GAME_DURATION, gameDuration)
        }
        // Вставка строки и получение ID новой записи
        val id = db.insert(TABLE_NAME, null, values)
        db.close() // Закрываем базу данных
        return id // Возвращаем ID новой записи
    }

    fun getAllGameStatistics(): List<GameStatistics> {
        val statisticsList = mutableListOf<GameStatistics>()
        val db = this.readableDatabase
        val cursor: Cursor = db.query(TABLE_NAME, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val idIndex = cursor.getColumnIndex(COLUMN_ID)
                val totalClicksIndex = cursor.getColumnIndex(COLUMN_TOTAL_CLICKS)
                val mouseClicksIndex = cursor.getColumnIndex(COLUMN_MOUSE_CLICKS)
                val hitPercentageIndex = cursor.getColumnIndex(COLUMN_HIT_PERCENTAGE)
                val gameDurationIndex = cursor.getColumnIndex(COLUMN_GAME_DURATION)

                // Проверяем, что индексы не равны -1
                if (idIndex != -1 && totalClicksIndex != -1 && mouseClicksIndex != -1 &&
                    hitPercentageIndex != -1 && gameDurationIndex != -1) {

                    val totalClicks = cursor.getInt(totalClicksIndex)
                    val mouseClicks = cursor.getInt(mouseClicksIndex)
                    val hitPercentage = cursor.getFloat(hitPercentageIndex)
                    val gameDuration = cursor.getFloat(gameDurationIndex)

                    val gameStatistics = GameStatistics( totalClicks, mouseClicks, hitPercentage, gameDuration)
                    statisticsList.add(gameStatistics)
                }
            } while (cursor.moveToNext())
        }
        cursor.close() // Закрываем курсор
        db.close() // Закрываем базу данных
        return statisticsList // Возвращаем список статистики игр
    }

    companion object {
        private const val DATABASE_NAME = "database.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME: String = "game_statistics"
        const val COLUMN_ID: String = "id"
        const val COLUMN_TOTAL_CLICKS: String = "totalClicks"
        const val COLUMN_MOUSE_CLICKS: String = "mouseClicks"
        const val COLUMN_HIT_PERCENTAGE: String = "hitPercentage"
        const val COLUMN_GAME_DURATION: String = "gameDuration"

        private const val TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TOTAL_CLICKS + " INTEGER, " +
                COLUMN_MOUSE_CLICKS + " INTEGER, " +
                COLUMN_HIT_PERCENTAGE + " REAL, " +
                COLUMN_GAME_DURATION + " REAL);"
    }
}
// Класс для представления статистики игры
data class GameStatistics(
    val totalClicks: Int,
    val mouseClicks: Int,
    val hitPercentage: Float,
    val gameDuration: Float
)