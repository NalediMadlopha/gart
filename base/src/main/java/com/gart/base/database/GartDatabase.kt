package com.gart.base.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gart.base.model.GithubRepository

@Database(entities = [(GithubRepository::class)], version = 6)
abstract class GartDatabase : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao

    companion object {
        @Volatile
        private var INSTANCE: GartDatabase? = null

        fun getInstance(context: Context): GartDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GartDatabase::class.java,
                    "gart_db"
                )
                .fallbackToDestructiveMigration()
                .build()

                INSTANCE = instance
                return instance
            }
        }
    }

}