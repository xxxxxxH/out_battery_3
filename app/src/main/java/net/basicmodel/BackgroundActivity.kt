package net.basicmodel

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.layout_list.*
import net.adapter.BackgroundAdapter
import net.event.MessageEvent
import net.utils.Constant
import net.utils.ResourceManager
import org.greenrobot.eventbus.EventBus
import java.util.prefs.BackingStoreException

class BackgroundActivity : BaseActivity() {
    var data :ArrayList<String> = ArrayList()
    override fun getLayout(): Int {
        return R.layout.layout_list
    }

    override fun initView() {

    }

    override fun initData() {
        data = ResourceManager.get().getBackgroundData()
        val adapter = BackgroundAdapter(this,recycler,R.layout.layout_item)
        adapter.data = data
        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = adapter
        adapter.setOnRVItemClickListener { parent, itemView, position ->
            EventBus.getDefault().post(MessageEvent(Constant.KEY_BG,adapter.data[position]))
            finish()
        }
    }

    override fun granted() {
        initData()
    }

    override fun denied() {

    }

    override fun neverAsk() {

    }
}