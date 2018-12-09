package com.gart.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.RoomWarnings
import com.gart.model.GithubRepositoryItem

@Dao
interface RepositoryDao {

    @Insert(onConflict = REPLACE)
    fun insertAll(githubRepositories: List<GithubRepositoryItem>)

    @Query("SELECT * FROM githubRepositoryItem")
    fun getAllRepositories(): LiveData<List<GithubRepositoryItem>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT id, full_name, description, forks_count, git_url,language,githubRepositoryLicense,name,open_issues_count,githubRepositoryOwner,stargazers_count,updated_at,url,watchers_count FROM githubRepositoryItem")
    fun getRepositoriesMinimal(): LiveData<List<GithubRepositoryItem>>

    @Query("SELECT * FROM githubRepositoryItem WHERE id = :id")
    fun getRepository(id: Int): LiveData<GithubRepositoryItem>

}