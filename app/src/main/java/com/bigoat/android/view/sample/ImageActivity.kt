package com.bigoat.android.view.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bigoat.android.view.sample.databinding.ImageActivityBinding

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.image_activity)
        val bind: ImageActivityBinding = ImageActivityBinding.inflate(layoutInflater)
        bind.lifecycleOwner = this
        setContentView(bind.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        bind.image = ImageSrc("https://www.baidu.com/img/flexible/logo/pc/result.png")
    }

    fun clickButton(view: View?) {}
}