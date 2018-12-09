package com.gart.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gart.model.GithubRepositoryItem
import com.gart.model.GithubRepositoryLicense
import com.gart.model.GithubRepositoryOwner

@Database(entities = [(GithubRepositoryItem::class), (GithubRepositoryLicense::class), (GithubRepositoryOwner::class)], version = 1)
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