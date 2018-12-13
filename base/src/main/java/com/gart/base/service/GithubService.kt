package com.gart.base.service

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubService {

    @GET("/search/repositories")
    fun searchRepositories(@Query("q") query: String, @Query("sort") sort: String,
                           @Query("order") order: String): Call<JSONObject>

    companion object {
        const val BASE_URL = "https://api.github.com/"

        fun getInstance() : GithubService {
            return Retrofit.Builder()
                .baseUrl(GithubService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService::class.java)
        }
    }


}