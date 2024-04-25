package com.bigoat.android.view.sample

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bigoat.android.view.sample.databinding.SidebarActivityBinding
import com.bigoat.android.view.old.SidebarItem
import com.bigoat.android.view.old.SidebarView
import com.bigoat.android.view.old.SimpleAdapter

class SidebarActivity : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: SidebarActivityBinding =
            SidebarActivityBinding.inflate(getLayoutInflater(), null, false)
        setContentView(binding.getRoot())
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        val viewPager2: ViewPager2 = findViewById(R.id.viewPage)
        initSidebarView(viewPager2)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initSidebarView(viewPager: ViewPager2) {

        viewPager.adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
            override fun createFragment(position: Int): Fragment {
                return BlankFragment.newInstance("Fragment $position")
            }

            override fun getItemCount(): Int {
                return 0
            }
        }

//        simpleAdapter.bindViewPager(viewPager)
    }

    fun clickButton(view: View?) {}
}