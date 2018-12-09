package com.gart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "license")
class GithubRepositoryLicense(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "key")
    val key: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "node_id")
    val nodeId: String,
    @ColumnInfo(name = "spdx_id")
    val spdxId: String,
    @ColumnInfo(name = "url")
    val url: String
)