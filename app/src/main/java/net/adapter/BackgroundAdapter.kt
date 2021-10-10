package net.adapter

import android.app.Activity
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper
import com.bumptech.glide.Glide
import net.basicmodel.R
import net.utils.ScreenUtils

class BackgroundAdapter(
    val activity: Activity,
    recyclerView: RecyclerView?,
    defaultItemLayoutId: Int
) :
    BGARecyclerViewAdapter<String>(recyclerView, defaultItemLayoutId) {
    override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: String?) {
        helper!!.getView<ImageView>(R.id.item_img).let {
            it.layoutParams = it.layoutParams.apply {
                width = (ScreenUtils.getScreenSize(activity)[1] / 2) - 10
                height = ScreenUtils.getScreenSize(activity)[0] / 2
            }
            Glide.with(activity).load(model).into(it)
        }
    }
}