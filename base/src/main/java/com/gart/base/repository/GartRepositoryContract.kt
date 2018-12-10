package com.gart.base.repository

import androidx.lifecycle.LiveData
import com.gart.base.model.GithubRepositoryItem

interface GartRepositoryContract {

    fun getGithubRepositories(): LiveData<List<GithubRepositoryItem>>

    fun getGithubRepository(repositoryId: Int): LiveData<GithubRepositoryItem>

}
