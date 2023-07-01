package com.example.wallnoire.presentation.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.wallnoire.R
import com.example.wallnoire.base.extention.activityComponent

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        this.activityComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

    companion object {
        fun start(activity: Activity) =
            activity.startActivity(Intent(activity, HomeActivity::class.java))
    }
}