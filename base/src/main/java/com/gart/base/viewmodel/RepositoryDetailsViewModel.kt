package com.gart.base.viewmodel

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gart.base.model.GithubRepository
import com.gart.base.repository.GartRepository


class RepositoryDetailsViewModel @VisibleForTesting constructor(
    app: Application,
    private val repository: GartRepository
) : AndroidViewModel(app) {

    constructor(app: Application) : this(app, GartRepository(app))

    fun getGithubRepository(repositoryId: Int) : LiveData<GithubRepository> {
        return repository.getGithubRepository(repositoryId)
    }

}