package com.example.wallnoire.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wallnoire.R
import com.example.wallnoire.base.extention.activityComponent
import com.example.wallnoire.presentation.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.activityComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        HomeActivity.start(this)
        supportFinishAfterTransition()
    }
}