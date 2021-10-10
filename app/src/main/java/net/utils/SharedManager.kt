package net.utils

import android.annotation.SuppressLint
import android.content.Context
import java.util.*

class SharedManager {
    companion object {
        private var i: SharedManager? = null
            get() {
                field ?: run {
                    field = SharedManager()
                }
                return field
            }

        @Synchronized
        fun get(): SharedManager {
            return i!!
        }
    }

    fun put(context: Context, key: String, content: Any) {
        val sp = context.getSharedPreferences("save", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(key, content as String)
        editor.apply()
        editor.commit()
    }

    fun getValue(context: Context, key: String, defaultValue: Any): Any? {
        return context.getSharedPreferences("save", Context.MODE_PRIVATE).getString(key, defaultValue as String)
    }
}