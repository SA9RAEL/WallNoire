package com.example.wallnoire.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wallnoire.R
import com.example.wallnoire.presentation.home.HomeActivity
import dagger.android.support.DaggerAppCompatActivity

class SplashActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        HomeActivity.start(this)
        supportFinishAfterTransition()
    }
}