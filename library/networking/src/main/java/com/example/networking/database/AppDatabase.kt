package com.example.networking.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.networking.database.favouritetrendingproject.dao.FavouriteTrendingProjectsDao
import com.example.networking.database.favouritetrendingproject.entity.FavouriteTrendingProjectEntity

@SuppressWarnings("TooManyFunctions")
@Database(
    version = AppDatabase.DB_VERSION,
    entities = [
        FavouriteTrendingProjectEntity::class,
    ],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {


    abstract fun getFavouriteTrendingProjectsDao(): FavouriteTrendingProjectsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        const val DB_VERSION = 1

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase =
            Room
                .databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase.db"
                )
                .build()
    }
}