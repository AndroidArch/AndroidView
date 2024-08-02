package com.bigoat.android.view.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.bigoat.android.view.icon.IconView
import com.bigoat.android.view.navbar.NavbarView
import com.blankj.utilcode.util.KeyboardUtils
import java.lang.reflect.Field

class IconActivity : AppCompatActivity() {

    private val iconViewList = mutableListOf<View>()

    @SuppressLint("MissingInflatedId", "CutPasteId", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icon)


        loadAllIconView()
    }

    private fun loadAllIconView() {
        val nav: NavbarView = findViewById(R.id.nav)

        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        val searchView = findViewById<SearchView>(R.id.search)
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 在用户提交查询文本时调用
                return true
            }

            override fun onQueryTextChange(name: String?): Boolean {
                iconViewList.forEach {
                    val show: Boolean =
                        name?.let { it1 -> (it.tag as String).contains(it1) } == true
                    when (show) {
                        true -> it.visibility = View.VISIBLE
                        else -> it.visibility = View.GONE
                    }
                }
                return true
            }
        })


        Handler(Looper.getMainLooper()).postDelayed({
            runOnUiThread {
                getAllIconResources(com.bigoat.android.view.R.string::class.java).forEach { (key, value) ->
                    val childView = layoutInflater.inflate(R.layout.icon_item, null)
                    val iconView: IconView = childView.findViewById(R.id.icon)
                    iconView.setName(value)

                    val name = key.replace("icon_", "")
                    val nameView: TextView = childView.findViewById(R.id.name)
                    nameView.text = name

                    val params = GridLayout.LayoutParams()
                    params.width = 0
                    params.height = GridLayout.LayoutParams.WRAP_CONTENT
                    params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    childView.layoutParams = params
                    gridLayout.addView(childView)

                    childView.tag = name
                    iconViewList.add(childView)
                }
                nav.rightView.apply {
                    visibility = View.VISIBLE
                    text = iconViewList.size.toString()
                }

                findViewById<ProgressBar>(R.id.progress).visibility = View.GONE
            }
        }, 100)

    }

    private fun getAllIconResources(clazz: Class<*>): Map<String, Int> {
        val fields: Array<Field> = clazz.fields
        val stringResources = mutableMapOf<String, Int>()

        for (field in fields) {
            if (field.type == Int::class.java) {
                val resourceId = field.getInt(null)
                val resourceName = field.name
                stringResources[resourceName] = resourceId
            }
        }

        return stringResources
    }


    override fun onResume() {
        super.onResume()
        KeyboardUtils.hideSoftInput(this)
    }

}