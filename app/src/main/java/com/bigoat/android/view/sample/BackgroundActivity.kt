package com.bigoat.android.view.sample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bigoat.android.view.background.BackgroundView

class BackgroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background)

        val textView = findViewById<TextView>(R.id.text2)

//        BackgroundView.load(textView, "#FF0000", true)
    }
}