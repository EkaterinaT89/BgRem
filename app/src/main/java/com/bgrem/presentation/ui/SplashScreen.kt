package com.bgrem.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bgrem.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private val delayHandler: Handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SPLASH_DELAY: Long = 1000
    }

    private val runnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        delayHandler.postDelayed(runnable, SPLASH_DELAY)
    }

    public override fun onDestroy() {
        delayHandler.removeCallbacks(runnable)
        super.onDestroy()
    }
}
