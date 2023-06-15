package com.example.wallnoire.presentation.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.wallnoire.R
import dagger.android.support.DaggerAppCompatActivity

class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

    companion object {
        fun start(activity: Activity) = activity.startActivity(Intent(activity, HomeActivity::class.java))
    }
}