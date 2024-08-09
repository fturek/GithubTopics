package com.example.githubtopics

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initExceptionLogger()
    }

    private fun initExceptionLogger() {
        Timber.plant(Timber.DebugTree())
    }
}