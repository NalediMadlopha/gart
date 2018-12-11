package com.gart.base.di

import android.app.Application
import android.content.Context
import com.gart.base.database.GartDatabase
import com.gart.base.database.RepositoryDao
import com.gart.base.repository.GartRepository
import com.gart.base.service.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class AppModule(private val app: Application) {

    @Provides
    fun context(): Context {
        return app
    }

    @Singleton
    @Provides
    fun provideRepository(): GartRepository {
        return GartRepository()
    }

    @Singleton
    @Provides
    fun provideRepositoryDao(): RepositoryDao {
        return GartDatabase.getInstance(app).repositoryDao()
    }

    @Singleton
    @Provides
    fun provideGithubService(): GithubService {
        return Retrofit.Builder()
            .baseUrl(GithubService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }

}
