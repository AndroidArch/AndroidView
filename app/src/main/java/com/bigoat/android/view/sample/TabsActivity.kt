package com.bigoat.android.view.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bigoat.android.view.button.ButtonView
import com.bigoat.android.view.tabs.TabsView
import com.google.android.material.tabs.TabLayout

class TabsActivity : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        // 设置状态栏白色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        val tab : TabLayout = TabLayout(this)
//        tab.addTab(tab.newTab().setText("Tab 2"))
//        tab.addTab(tab.newTab().setText("Tab 3"))
//        tab.addTab(tab.newTab().setText("Tab 4"))

    }

    fun clickButton(view: View) {
        if (view is ButtonView) {
            Toast.makeText(this, "点击按钮 " + (view as ButtonView).text, Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(this, "点击了按钮", Toast.LENGTH_SHORT).show()
        }
    }
}