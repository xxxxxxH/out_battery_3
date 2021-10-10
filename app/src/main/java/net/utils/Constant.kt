package net.utils

import android.Manifest
import net.basicmodel.R

object Constant {
    const val TYPE_BG = "bg"
    const val TYPE_ANIM = "anim"
    const val TYPE = "type"
    const val BATTERY_CHANGED = "BATTERY_CHANGED"
    const val POWER_CONNECTED = "POWER_CONNECTED"
    const val KEY_BG = "key_bg"
    const val KEY_ANIM = "anim"
    var permission = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    const val defaultUrl = "https://magichua.club/preview/img/bg_1.jpg"
}