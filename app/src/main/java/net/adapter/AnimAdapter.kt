package net.adapter

import android.app.Activity
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper
import com.bumptech.glide.Glide
import net.basicmodel.R
import net.entity.ResourceEntity
import net.utils.ScreenUtils

/**
 * Copyright (C) 2021,2021/10/11, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class AnimAdapter(val activity: Activity, recyclerView: RecyclerView?, defaultItemLayoutId: Int) :
    BGARecyclerViewAdapter<ResourceEntity>(recyclerView, defaultItemLayoutId) {
    override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: ResourceEntity?) {
        helper!!.getView<ImageView>(R.id.item_img).let {
            it.layoutParams = it.layoutParams.apply {
                width = (ScreenUtils.getScreenSize(activity)[1] / 3) - 10
                height = (ScreenUtils.getScreenSize(activity)[1] / 3) - 10
            }
            Glide.with(activity).load(model!!.id).into(it)
        }
    }
}