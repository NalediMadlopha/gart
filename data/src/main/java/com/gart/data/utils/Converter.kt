package com.gart.data.utils

import androidx.room.TypeConverter
import com.gart.data.model.GithubRepositoryItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converter {

    @TypeConverter
    fun stringToLicense(value: String): GithubRepositoryItem.License? {
        if (value != "") {
            val listType = object : TypeToken<GithubRepositoryItem.License>() {}.type
            return Gson().fromJson<GithubRepositoryItem.License>(value, listType)
        }
        return null
    }

    @TypeConverter
    fun licenseToString(githubRepositoryLicense: GithubRepositoryItem.License): String {
        return Gson().toJson(githubRepositoryLicense)
    }

    @TypeConverter
    fun stringToOwner(value: String): GithubRepositoryItem.Owner? {
        if (value != "") {
            val listType = object : TypeToken<GithubRepositoryItem.Owner>() {}.type
            return Gson().fromJson<GithubRepositoryItem.Owner>(value, listType)
        }
        return null
    }

    @TypeConverter
    fun ownerToString(githubRepositoryOwner: GithubRepositoryItem.Owner): String {
        return Gson().toJson(githubRepositoryOwner)
    }

}