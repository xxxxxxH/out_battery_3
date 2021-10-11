package net.basicmodel

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_charging.*
import net.event.MessageEvent
import net.utils.Constant
import net.utils.ResourceManager
import net.utils.SharedManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ChargingActivity : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.layout_charging
    }

    override fun initView() {
        Glide.with(this)
            .load(SharedManager.get().getValue(this, Constant.KEY_BG, Constant.defaultUrl))
            .into(bg)
        Glide.with(this)
            .load(
                SharedManager.get().getValue(
                    this,
                    Constant.KEY_ANIM,
                    ResourceManager.get().res2String(this, R.mipmap.anim1)
                )
            )
            .into(anim)
    }

    override fun initData() {

    }

    override fun granted() {
        EventBus.getDefault().register(this)
        initView()
        addReceiver()
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: MessageEvent) {
        val msg = event.getMessage()
        when (msg[0]) {
            Constant.BATTERY_CHANGED -> power.text = """
                                                         current power
                                                         ${msg[1]}%
                                                         """.trimIndent()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun denied() {

    }

    override fun neverAsk() {

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}