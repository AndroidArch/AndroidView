package com.bigoat.android.view.sample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val outPath: String = cacheDir.path + "/icon.xml"

        Utils.parseTTFToFile(this, "uview.ttf", outPath)

        val str = "58924"

        for (i in 0 until str.length) {
            val c = str[i]
            val unicode = c.code
            println("Unicode of $c is: $unicode")
        }
    }


    fun jumpToColorActivity(view: View) {
        startActivity(Intent(this, ColorActivity::class.java))
    }

    fun jumpToIconActivity(view: View) {
        Toast.makeText(this, "暂不支持", Toast.LENGTH_SHORT).show()
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

    fun clickJump(view: View) {
        Toast.makeText(this, "暂不支持", Toast.LENGTH_SHORT).show()
    }


    fun jumpToBackgroundActivity(view: View) {
        startActivity(Intent(this, BackgroundActivity::class.java))
    }

    fun jumpToTabbarActivity(view: View) {
        startActivity(Intent(this, TabbarActivity::class.java))
    }

    fun openActivity(view: View) {
        when (view.id) {
            R.id.icon -> startActivity(Intent(this, IconActivity::class.java))
        }
    }

}