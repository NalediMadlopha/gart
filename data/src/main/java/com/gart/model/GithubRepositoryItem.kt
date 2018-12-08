package com.gart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gart.utils.Converter

@Entity(tableName = "githubRepositoryItem")
@TypeConverters(Converter::class)
class GithubRepositoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "forks_count")
    val forksCount: Int,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "git_url")
    val gitUrl: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "githubRepositoryLicense")
    val githubRepositoryLicense: GithubRepositoryLicense,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "open_issues_count")
    val openIssuesCount: Int,
    @ColumnInfo(name = "githubRepositoryOwner")
    val githubRepositoryOwner: GithubRepositoryOwner,
    @ColumnInfo(name = "stargazers_count")
    val stargazersCount: Int,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "watchers_count")
    val watchersCount: Int
)
