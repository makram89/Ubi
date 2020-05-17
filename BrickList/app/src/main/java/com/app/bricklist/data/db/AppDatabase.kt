package com.app.bricklist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.bricklist.data.models.*


@Database(
    entities = [Categories::class, Codes::class, Colors::class, Inventories::class, InventoriesParts::class, ItemTypes::class, Parts::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun brickDao(): BrickDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance
                ?: synchronized(this) {
                    instance
                        ?: buildDatabase(
                            context
                        ).also { instance = it }
                }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "Brick.db")
                .createFromAsset("BrickList.db")
                .build()
        }

        private fun destroyDataBase() {
            instance = null
        }
    }
}