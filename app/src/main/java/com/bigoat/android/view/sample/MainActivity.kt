package com.bigoat.android.view.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, BackgroundActivity::class.java))
    }

    fun jumpToButtonActivity(view: View?) {
        startActivity(Intent(this, ButtonActivity::class.java))
    }

    fun jumpToImageActivity(view: View?) {
        startActivity(Intent(this, ImageActivity::class.java))
    }

    fun jumpToBadgeActivity(view: View) {
        startActivity(Intent(this, BadgeActivity::class.java))
    }

    fun jumpToBackgroundActivity(view: View) {
        startActivity(Intent(this, BackgroundActivity::class.java))
    }

    fun jumpToTabbarActivity(view: View) {
        startActivity(Intent(this, TabbarActivity::class.java))
    }
}