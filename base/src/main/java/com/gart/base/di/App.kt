package com.gart.base.di

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = getAppComponent(this)
    }

    companion object {

        private lateinit var appComponent: AppComponent

        fun appComponent(): AppComponent {
            return appComponent
        }

        private fun getAppComponent(app: Application): AppComponent {
            return DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
        }
    }
}