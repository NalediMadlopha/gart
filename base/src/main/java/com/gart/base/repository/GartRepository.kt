package com.gart.base.repository

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import com.gart.base.database.GartDatabase
import com.gart.base.database.RepositoryDao
import com.gart.base.model.GithubRepository
import com.gart.base.service.GithubService
import com.google.gson.JsonObject
import retrofit2.Response


open class GartRepository
@VisibleForTesting constructor(
    private val githubService: GithubService, private val repositoryDao: RepositoryDao
) : GartRepositoryContract {

    constructor(application: Application) : this(GithubService.getInstance(), GartDatabase.getInstance(application).repositoryDao())

    override fun fetchGithubRepositories(): Response<JsonObject>? {
        return githubService.searchRepositories(QUERY, SORT, ORDER).execute()
    }

    override fun getGithubRepositories(): LiveData<List<GithubRepository>> {
        return repositoryDao.getAllRepositories()
    }

    override fun getGithubRepository(repositoryId: Int): LiveData<GithubRepository> {
        return repositoryDao.getRepository(repositoryId)
    }

    override fun saveGithubRepositories(githubRepositoryList: List<GithubRepository>) {
        repositoryDao.insertAll(githubRepositoryList)
    }

    companion object {
        const val QUERY = "android+language:kotlin+language:java"
        const val SORT = "stars"
        const val ORDER = "desc"
    }

}