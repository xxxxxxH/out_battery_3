package net.basicmodel

import android.annotation.SuppressLint
import android.content.Intent
import com.bumptech.glide.Glide
import com.jyuesong.android.floatactionview.FloatActionView
import kotlinx.android.synthetic.main.activity_main.*
import net.event.MessageEvent
import net.utils.Constant
import net.utils.ResourceManager
import net.utils.SharedManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity(), FloatActionView.OnClick {

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    @SuppressLint("CheckResult", "ResourceAsColor")
    override fun initView() {
        EventBus.getDefault().register(this)
        floatView.setMainButtonIcon(R.mipmap.add)
        floatView.setData(
            ResourceManager.get().initTipsData(),
            ResourceManager.get().initImagesData()
        )
        floatView.setOnClick(this)
        Glide.with(this)
            .load(SharedManager.get().getValue(this, Constant.KEY_BG, Constant.defaultUrl))
            .into(background)
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

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: MessageEvent) {
        val msg = event.getMessage()
        when (msg[0]) {
            Constant.BATTERY_CHANGED -> powerTv.text = """
                                                         current power
                                                         ${msg[1]}%
                                                         """.trimIndent()
            Constant.POWER_CONNECTED -> {
                val intent = Intent(this, ChargingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            Constant.KEY_BG -> {
                Glide.with(this).load(msg[1]).into(background)
                SharedManager.get().put(this, Constant.KEY_BG, msg[1] as String)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun granted() {
        initView()
        addReceiver()
    }

    override fun denied() {

    }

    override fun neverAsk() {

    }


    override fun positionClicked(position: Int) {
        when (position) {
            0 -> startActivity(Intent(this, BackgroundActivity::class.java))
        }
    }

    override fun mainClicked() {
    }

    override fun dismissed() {
    }

}