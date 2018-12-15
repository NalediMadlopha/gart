package com.gart.base.viewmodel

interface RepositoryListViewContract {

    fun update()

    fun displayLocalRepositories()

    fun displayUpdateErrorNotification()

    fun displayNoLocalRepositoriesErrorNotification()

}
