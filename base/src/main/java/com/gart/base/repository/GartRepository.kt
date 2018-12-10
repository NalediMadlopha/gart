package com.gart.base.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gart.base.database.RepositoryDao
import com.gart.base.model.GithubRepositoryItem
import com.gart.base.service.GithubService
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GartRepository(private val githubService: GithubService, private val repositoryDao: RepositoryDao) : GartRepositoryContract {

    override fun getGithubRepositories(): LiveData<List<GithubRepositoryItem>> {
        updateRepositoryDatabase()
        return repositoryDao.getAllRepositories()
    }

    override fun getGithubRepository(repositoryId: Int): LiveData<GithubRepositoryItem> {
        return repositoryDao.getRepository(repositoryId)
    }

    private fun updateRepositoryDatabase() {
        val data = MutableLiveData<List<GithubRepositoryItem>>()

        githubService.searchRepositories(QUERY, SORT, ORDER).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val itemsJsonArray = (response.body()?.get(ITEMS) as JsonArray)

                    data.value = jsonArrayToList(itemsJsonArray)
                    AsyncTask.execute { repositoryDao.insertAll(data.value!!) }
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                // TODO: I have left out the error case for brevity - Handle the error case
            }
        })
    }

    private fun jsonArrayToList(jsonArray: JsonArray): List<GithubRepositoryItem> {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.fromJson(jsonArray, Array<GithubRepositoryItem>::class.java).toList()
    }

    companion object {
        private const val QUERY = "android+language:kotlin+language:java"
        private const val SORT = "stars"
        private const val ORDER = "desc"
        private const val ITEMS = "items"
    }

}