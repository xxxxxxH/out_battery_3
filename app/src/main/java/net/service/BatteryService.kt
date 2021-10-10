package net.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import net.receiver.BatteryReceiver

class BatteryService:Service() {

    val receiver = BatteryReceiver()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        val intent = IntentFilter()
        intent.addAction(Intent.ACTION_BATTERY_CHANGED)
        intent.addAction(Intent.ACTION_POWER_CONNECTED)
        registerReceiver(receiver,intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}