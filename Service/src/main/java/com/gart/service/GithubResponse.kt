package com.gart.service

import com.google.gson.annotations.SerializedName

class GithubResponse(
    @SerializedName("items")
    val items: List<Item>
) {
    data class Item(
        @SerializedName("description")
        val description: String,
        @SerializedName("forks_count")
        val forksCount: Int,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("git_url")
        val gitUrl: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("language")
        val language: String,
        @SerializedName("license")
        val license: License,
        @SerializedName("name")
        val name: String,
        @SerializedName("open_issues_count")
        val openIssuesCount: Int,
        @SerializedName("owner")
        val owner: Owner,
        @SerializedName("stargazers_count")
        val stargazersCount: Int,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("watchers_count")
        val watchersCount: Int
    ) {
        data class License(
            @SerializedName("key")
            val key: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("node_id")
            val nodeId: String,
            @SerializedName("spdx_id")
            val spdxId: String,
            @SerializedName("url")
            val url: String
        )

        data class Owner(
            @SerializedName("login")
            val login: String
        )
    }
}