package com.gart.base.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.gart.base.model.GithubRepository

@Dao
interface RepositoryDao {

    @Insert(onConflict = REPLACE)
    fun insertAll(githubRepositories: List<GithubRepository>)

    @Query("SELECT * FROM item ORDER BY stargazers_count DESC")
    fun getAllRepositories(): LiveData<List<GithubRepository>>

    @Query("SELECT * FROM item WHERE id = :id")
    fun getRepository(id: Int): LiveData<GithubRepository>

}