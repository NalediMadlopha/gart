package com.gart.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gart.data.utils.Converter

@Entity(tableName = "item")
@TypeConverters(Converter::class)
data class GithubRepositoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "forks_count")
    val forks_count: Int,
    @ColumnInfo(name = "full_name")
    val full_name: String,
    @ColumnInfo(name = "git_url")
    val git_url: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "license")
    val license: License,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "open_issues_count")
    val open_issues_count: Int,
    @ColumnInfo(name = "owner")
    val owner: Owner,
    @ColumnInfo(name = "stargazers_count")
    val stargazers_count: Int,
    @ColumnInfo(name = "updated_at")
    val updated_at: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "watchers_count")
    val watchers_count: Int
) {
    @Entity(tableName = "license")
    @TypeConverters(Converter::class)
    data class License(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = "key")
        val key: String,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "node_id")
        val node_id: String,
        @ColumnInfo(name = "spdx_id")
        val spdx_id: String,
        @ColumnInfo(name = "url")
        val url: String,
        @ColumnInfo(name = "item_id")
        val item_id: Int
    )

    @Entity(tableName = "owner")
    @TypeConverters(Converter::class)
    data class Owner(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = "login")
        val login: String,
        @ColumnInfo(name = "item_id")
        val item_id: Int
    )
}