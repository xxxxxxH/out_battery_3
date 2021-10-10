package net.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import net.event.MessageEvent
import net.utils.Constant
import org.greenrobot.eventbus.EventBus

class BatteryReceiver:BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        when(p1!!.action){
            Intent.ACTION_BATTERY_CHANGED -> {
                val scale = p1.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val rawLevel = p1.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val percentage = rawLevel.toFloat() / scale.toFloat()
                val level = (percentage * 100).toInt()
                EventBus.getDefault().post(MessageEvent(Constant.BATTERY_CHANGED,level.toString()))
            }
            Intent.ACTION_POWER_CONNECTED -> {
                EventBus.getDefault().post(MessageEvent(Constant.POWER_CONNECTED))
            }
        }
    }
}