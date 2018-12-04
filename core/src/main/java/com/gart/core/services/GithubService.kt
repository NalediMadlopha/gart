package com.gart.core.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubService {

    @GET("/search/repositories")
    fun searchRepositories(@Query("q") query: String, @Query("sort") sort: String,
                           @Query("order") order: String): Call<GithubResponse>

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

}