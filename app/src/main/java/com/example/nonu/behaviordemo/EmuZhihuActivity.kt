package com.example.nonu.behaviordemo

import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.example.nonu.behaviordemo.base.BaseActivity
import com.example.nonu.behaviordemo.base.BaseRecyclerAdapter
import com.example.nonu.behaviordemo.util.ToastUtil
import kotlinx.android.synthetic.main.activity_emu_zhihu.*

/**
 * <pre>
 *     author : Nonu
 *     e-mail : 1162365377@qq.com
 *     time   : 2018/5/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class EmuZhihuActivity : BaseActivity() {
    private lateinit var mAdapter: BaseRecyclerAdapter

    override fun getLayoutRes(): Int {
        return R.layout.activity_emu_zhihu
    }

    override fun initView() {
        recycler_rv.layoutManager = LinearLayoutManager(this)
        recycler_rv.adapter = mAdapter

    }

    override fun initData() {
        mAdapter = BaseRecyclerAdapter(this, R.layout.item_rv)
        mAdapter.addBindViewHelper { holder, position ->
            val str = position.toString()
            holder.itemView.findViewById<TextView>(R.id.item_tv).text = str
        }
        mAdapter.addItemClick(R.id.item_tv, fun(v: View, p: Int) {
            ToastUtil.showShortToast(this, p.toString())
        })
        for (i in 0..40) mAdapter.addData(HashMap())
    }

    override fun setDisplayHomeAsUpEnabled(): Boolean {
        return true
    }
}