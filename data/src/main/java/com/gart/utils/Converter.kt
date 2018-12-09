package com.gart.utils

import androidx.room.TypeConverter
import com.gart.model.GithubRepositoryLicense
import com.gart.model.GithubRepositoryOwner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converter {

    @TypeConverter
    fun stringToLicense(value: String): GithubRepositoryLicense? {
        if (value != "") {
            val listType = object : TypeToken<GithubRepositoryLicense>() {}.type
            return Gson().fromJson<GithubRepositoryLicense>(value, listType)
        }
        return null
    }

    @TypeConverter
    fun licenseToString(githubRepositoryLicense: GithubRepositoryLicense): String {
        return Gson().toJson(githubRepositoryLicense)
    }

    @TypeConverter
    fun stringToOwner(value: String): GithubRepositoryOwner? {
        if (value != "") {
            val listType = object : TypeToken<GithubRepositoryOwner>() {}.type
            return Gson().fromJson<GithubRepositoryOwner>(value, listType)
        }
        return null
    }

    @TypeConverter
    fun ownerToString(githubRepositoryOwner: GithubRepositoryOwner): String {
        return Gson().toJson(githubRepositoryOwner)
    }

}