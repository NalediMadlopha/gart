package com.gart.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.gart.data.model.GithubRepositoryItem

@Dao
interface RepositoryDao {

    @Insert(onConflict = REPLACE)
    fun insertAll(githubRepositories: List<GithubRepositoryItem>)

    @Query("SELECT * FROM item")
    fun getAllRepositories(): LiveData<List<GithubRepositoryItem>>

    @Query("SELECT * FROM item WHERE id = :id")
    fun getRepository(id: Int): LiveData<GithubRepositoryItem>

}