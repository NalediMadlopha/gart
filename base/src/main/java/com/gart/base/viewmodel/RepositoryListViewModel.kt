package com.gart.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gart.base.database.GartDatabase
import com.gart.base.model.GithubRepositoryItem
import com.gart.base.repository.GartRepository
import com.gart.base.service.GithubService
import com.gart.base.service.GithubService.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RepositoryListViewModel(application: Application) : AndroidViewModel(application) {

    private val gartRepository: GartRepository
    private val githubRepositoryList: LiveData<List<GithubRepositoryItem>>

    init {
        val githubService: GithubService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)

        val repositoryDao = GartDatabase.getInstance(application).getRepositoryDao()
        gartRepository = GartRepository(githubService, repositoryDao)

        githubRepositoryList = gartRepository.getGithubRepositories()
    }

    fun getGithubRepository(): LiveData<List<GithubRepositoryItem>> {
        return this.githubRepositoryList
    }

}