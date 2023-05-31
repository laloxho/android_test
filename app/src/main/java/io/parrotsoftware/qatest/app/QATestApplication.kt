package io.parrotsoftware.qatest.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.parrotsoftware.qatest.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class QATestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
