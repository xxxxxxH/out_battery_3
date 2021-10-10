package net.basicmodel

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tencent.mmkv.MMKV
import net.receiver.BatteryReceiver
import net.service.BatteryService
import net.utils.Constant
import net.utils.PermissionListener
import net.utils.PermissionsManager
import java.util.*

abstract class BaseActivity:AppCompatActivity(), PermissionListener {

    val receiver : BroadcastReceiver = BatteryReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        startService(Intent(this,BatteryService::class.java))
        PermissionsManager.get().requestPermissions(this,this)
    }

    fun addReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(receiver, intentFilter)
    }

    abstract fun getLayout():Int

    abstract fun initView()

    abstract fun initData()

}