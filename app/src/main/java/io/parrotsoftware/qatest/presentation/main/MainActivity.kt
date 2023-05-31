package io.parrotsoftware.qatest.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.coroutineScope
import dagger.hilt.android.AndroidEntryPoint
import io.parrotsoftware.qatest.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var keepSplashOnScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureSplashScreen()
        setContentView(R.layout.main_activity)
    }

    private fun configureSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplashOnScreen }

        lifecycle.coroutineScope.launch(Dispatchers.Main) {
            delay(1500)
            keepSplashOnScreen = false
        }
    }
}
