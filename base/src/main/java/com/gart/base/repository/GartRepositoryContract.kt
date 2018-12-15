package com.gart.base.repository

import androidx.lifecycle.LiveData
import com.gart.base.model.GithubRepository
import com.google.gson.JsonObject
import retrofit2.Response

interface GartRepositoryContract {

    fun fetchGithubRepositories(): Response<JsonObject>?

    fun getGithubRepositories(): LiveData<List<GithubRepository>>

    fun getGithubRepository(repositoryId: Int): LiveData<GithubRepository>

    fun saveGithubRepositories(githubRepositoryList: List<GithubRepository>)

}
