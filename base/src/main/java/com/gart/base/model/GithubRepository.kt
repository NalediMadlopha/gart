package com.gart.base.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class GithubRepository(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "forks_count")
    var forks_count: Int?,
    @ColumnInfo(name = "full_name")
    var full_name: String?,
    @ColumnInfo(name = "git_url")
    var git_url: String?,
    @ColumnInfo(name = "language")
    var language: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "open_issues_count")
    var open_issues_count: Int?,
    @ColumnInfo(name = "stargazers_count")
    var stargazers_count: Int?,
    @ColumnInfo(name = "updated_at")
    var updated_at: String?,
    @ColumnInfo(name = "url")
    var url: String?,
    @ColumnInfo(name = "watchers_count")
    var watchers_count: Int?
)