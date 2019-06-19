package com.example.emojigame.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.emojigame.Model.Puzzle

@Database(
    version = 1,
    entities = arrayOf(Puzzle::class)
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "puzzles.db"
                    ).build()
                }
                INSTANCE
            }
            return INSTANCE!!
        }
    }

    abstract fun puzzlesDao(): PuzzlesDao
}
