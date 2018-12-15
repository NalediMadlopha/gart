package com.gart.base.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.os.AsyncTask
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gart.base.model.GithubRepository
import com.gart.base.repository.GartRepository
import com.gart.base.utils.Utils
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Response


class RepositoryListViewModel
@VisibleForTesting constructor(
    private val app: Application,
    private val view: RepositoryListViewContract,
    private val repository: GartRepository,
    private val utils: Utils
) : AndroidViewModel(app) {

    @SuppressLint("VisibleForTests")
    constructor(view: RepositoryListViewContract, app: Application) : this(app, view, GartRepository(app), Utils())

    fun getGithubRepositories(): LiveData<List<GithubRepository>> {
        return repository.getGithubRepositories()
    }

    fun getGithubRepository(repositoryId: Int) : LiveData<GithubRepository> {
        return repository.getGithubRepository(repositoryId)
    }

    fun fetchGithubRepositories() {
        if (utils.isConnected(app.applicationContext)) {
            AsyncTask.execute {
                val response = repository.fetchGithubRepositories()
                saveGithubRepositories(response)
            }
        } else {
            view.displayNoLocalRepositoriesErrorNotification()
        }
    }

    private fun saveGithubRepositories(response: Response<JsonObject>?) {
        val body = response?.body()
        val items = body?.get(ITEMS)

        if (items != null && response.isSuccessful) {
            val itemsJsonArray = (items as JsonArray)
            val githubRepositoryList = convertJSONArrayToList(itemsJsonArray)

            AsyncTask.execute {
                repository.saveGithubRepositories(githubRepositoryList)
                view.update()
            }
        } else {
            view.displayUpdateErrorNotification()
        }
    }

    private fun convertJSONArrayToList(jsonArray: JsonArray): List<GithubRepository> {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.fromJson(jsonArray.toString(), Array<GithubRepository>::class.java).toList()
    }

    companion object {
        private const val ITEMS = "items"
    }

}