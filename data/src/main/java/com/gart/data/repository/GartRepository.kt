package com.gart.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.gart.data.database.RepositoryDao
import com.gart.data.model.GithubRepositoryItem
import com.gart.service.GithubService
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
        githubService.searchRepositories(QUERY, SORT, ORDER).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val jsonElement = (response.body()?.get("items") as JsonArray)

                val gson = GsonBuilder().setPrettyPrinting().create()
                val packagesArray = gson.fromJson(jsonElement , Array<GithubRepositoryItem>::class.java).toList()

                Log.d("Github response", response.body()?.get("items").toString())
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("Github test", t.message, t)
            }
        })
    }

    companion object {
        private const val QUERY = "android+language:kotlin+language:java"
        private const val SORT = "stars"
        private const val ORDER = "desc"
    }

}