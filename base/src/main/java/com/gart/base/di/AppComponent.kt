package com.gart.base.di

import com.gart.base.database.GartDatabase
import com.gart.base.repository.GartRepository
import com.gart.base.viewmodel.RepositoryListViewModel
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent {

    fun inject(viewModel: RepositoryListViewModel)

    fun inject(repository: GartRepository)

    fun inject(gartDatabase: GartDatabase)

}
