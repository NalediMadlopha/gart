package com.gart.data.repository

import androidx.lifecycle.LiveData
import com.gart.data.model.GithubRepositoryItem

interface GartRepositoryContract {

    fun getGithubRepositories(): LiveData<List<GithubRepositoryItem>>

    fun getGithubRepository(repositoryId: Int): LiveData<GithubRepositoryItem>

}
