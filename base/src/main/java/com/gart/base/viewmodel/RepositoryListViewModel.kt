package com.gart.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gart.base.di.App
import com.gart.base.model.GithubRepositoryItem
import com.gart.base.repository.GartRepository
import javax.inject.Inject


class RepositoryListViewModel : ViewModel() {

    @Inject
    lateinit var gartRepository: GartRepository

    private val githubRepositoryList: LiveData<List<GithubRepositoryItem>>

    init {
        App.appComponent().inject(this)

        githubRepositoryList = gartRepository.getGithubRepositories()
    }

    fun getGithubRepository(): LiveData<List<GithubRepositoryItem>> {
        return this.githubRepositoryList
    }

}