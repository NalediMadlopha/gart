package com.gart.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gart.data.model.GithubRepositoryItem

@Database(entities = [(GithubRepositoryItem::class), (GithubRepositoryItem.License::class), (GithubRepositoryItem.Owner::class)], version = 1)
abstract class GartDatabase : RoomDatabase() {

    abstract fun getRepositoryDao(): RepositoryDao

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
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }

}