package net.basicmodel

import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.layout_list.*
import net.adapter.AnimAdapter
import net.event.MessageEvent
import net.utils.Constant
import net.utils.ResourceManager
import org.greenrobot.eventbus.EventBus

/**
 * Copyright (C) 2021,2021/10/11, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class AnimActivity : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.layout_list
    }

    override fun initView() {
        val data =
            ResourceManager.get().getResourceByFolder(this, R.mipmap::class.java, "mipmap", "anim")
        val adapter = AnimAdapter(this, recycler, R.layout.layout_item)
        adapter.data = data
        recycler.layoutManager = GridLayoutManager(this, 3)
        recycler.adapter = adapter
        adapter.setOnRVItemClickListener { parent, itemView, position ->
            val entity = adapter.data[position]
            EventBus.getDefault().post(MessageEvent(Constant.KEY_ANIM, entity.id))
            finish()
        }
    }

    override fun initData() {

    }

    override fun granted() {
        initView()
    }

    override fun denied() {

    }

    override fun neverAsk() {

    }
}