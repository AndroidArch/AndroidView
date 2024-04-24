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
import com.bigoat.android.view.sidebar.SidebarItem
import com.bigoat.android.view.sidebar.SidebarView
import com.bigoat.android.view.sidebar.SimpleAdapter

class SidebarActivity : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: SidebarActivityBinding =
            SidebarActivityBinding.inflate(getLayoutInflater(), null, false)
        setContentView(binding.getRoot())
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        val viewPager2: ViewPager2 = findViewById<ViewPager2>(R.id.viewPage)
        initSidebarView(viewPager2)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initSidebarView(viewPager: ViewPager2) {
        val items: MutableList<SidebarItem> = ArrayList<SidebarItem>()
        for (i in 0..9) {
            val item = SidebarItem("Item " + (i + 1))
            item.size = intArrayOf(12, 24)
            item.color = intArrayOf(Color.parseColor("#FF0000"), Color.parseColor("#00FF00"))
            item.topIcon = arrayOf<Drawable>(
                resources.getDrawable(R.drawable.image_loading),
                resources.getDrawable(R.drawable.image_error)
            )
            items.add(item)
        }
        val sidebarView: SidebarView = findViewById(R.id.sidebarView)
        val simpleAdapter = SimpleAdapter(items)
        sidebarView.setAdapter(simpleAdapter)
        viewPager.adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
            override fun createFragment(position: Int): Fragment {
                return BlankFragment.newInstance("Fragment $position")
            }

            override fun getItemCount(): Int {
                return simpleAdapter.itemCount
            }
        }

        simpleAdapter.bindViewPager(viewPager)
    }

    fun clickButton(view: View?) {}
}