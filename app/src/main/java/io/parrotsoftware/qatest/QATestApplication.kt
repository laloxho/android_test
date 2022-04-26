package io.parrotsoftware.qatest

import android.app.Application
import timber.log.Timber

class QATestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}