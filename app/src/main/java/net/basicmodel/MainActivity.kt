package net.basicmodel

import android.annotation.SuppressLint
import android.content.Intent
import com.bumptech.glide.Glide
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.callback.SelectCallback
import com.huantansheng.easyphotos.models.album.entity.Photo
import com.jyuesong.android.floatactionview.FloatActionView
import kotlinx.android.synthetic.main.activity_main.*
import net.event.MessageEvent
import net.utils.Constant
import net.utils.GlideEngine
import net.utils.ResourceManager
import net.utils.SharedManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*


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
            Constant.KEY_ANIM -> {
                val animUrl = ResourceManager.get().res2String(this, msg[1] as Int)
                Glide.with(this).load(animUrl).into(anim)
                SharedManager.get().put(this, Constant.KEY_ANIM, animUrl)
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
            1 -> {
                EasyPhotos.createAlbum(this, false, false, GlideEngine.getInstance())
                    .start(object : SelectCallback() {
                        override fun onResult(photos: ArrayList<Photo>?, isOriginal: Boolean) {
                            Glide.with(this@MainActivity).load(photos!![0].path).into(background)
                            SharedManager.get()
                                .put(this@MainActivity, Constant.KEY_BG, photos[0].path)
                        }

                        override fun onCancel() {
                        }

                    })
            }
            2 -> startActivity(Intent(this, AnimActivity::class.java))
        }
    }

    override fun mainClicked() {
    }

    override fun dismissed() {
    }

}