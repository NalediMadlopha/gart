package com.gart.base.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.gart.base.model.GithubRepositoryItem

@Dao
interface RepositoryDao {

    @Insert(onConflict = REPLACE)
    fun insertAll(githubRepositories: List<GithubRepositoryItem>)

    @Query("SELECT * FROM item ORDER BY stargazers_count DESC")
    fun getAllRepositories(): LiveData<List<GithubRepositoryItem>>

    @Query("SELECT * FROM item WHERE id = :id")
    fun getRepository(id: Int): LiveData<GithubRepositoryItem>

}