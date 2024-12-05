package ru.afilonov.app1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.afilonov.app1.dao.HeroDao
import ru.afilonov.app1.entities.Hero
import ru.afilonov.app1.models.ApiResponse
import ru.afilonov.app1.utils.Converters

@Database(entities = [Hero::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "heroes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}