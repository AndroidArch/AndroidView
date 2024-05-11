package com.bigoat.android.view.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun jumpToColorActivity(view: View) {
        startActivity(Intent(this, ColorActivity::class.java))
    }

    fun jumpToIconActivity(view: View) {
        Toast.makeText(this, "暂不支持", Toast.LENGTH_SHORT).show()
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

    fun clickJump(view: View) {
        Toast.makeText(this, "暂不支持", Toast.LENGTH_SHORT).show()
    }

}