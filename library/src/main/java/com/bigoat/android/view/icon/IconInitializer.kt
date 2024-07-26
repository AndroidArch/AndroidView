package com.bigoat.android.view.icon

import android.content.Context
import androidx.startup.Initializer

class IconInitializer : Initializer<IconInitializer> {
    override fun create(context: Context): IconInitializer {
        IconInitializer.context = context
        return this
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

    companion object {
        var context: Context? = null

    }
}