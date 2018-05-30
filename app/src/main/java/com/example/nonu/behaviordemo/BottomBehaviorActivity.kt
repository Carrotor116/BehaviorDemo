package com.example.nonu.behaviordemo

import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.nonu.behaviordemo.base.BaseActivity
import com.example.nonu.behaviordemo.base.BaseRecyclerAdapter
import com.example.nonu.behaviordemo.util.ToastUtil
import kotlinx.android.synthetic.main.activity_bottom_behavior.*

/**
 * <pre>
 *     author : Nonu
 *     e-mail : 1162365377@qq.com
 *     time   : 2018/5/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class BottomBehaviorActivity : BaseActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var bottomSheetDialog: BottomSheetDialog


    override fun getLayoutRes(): Int {
        return R.layout.activity_bottom_behavior
    }

    override fun initView() {

        bottomSheetBehavior = BottomSheetBehavior.from(tab_ll)
        sheet_ctrl_btn.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        dialog_ctrl_btn.setOnClickListener {
            bottomSheetDialog.show()
        }
    }

    override fun initData() {
        val recyclerRv = RecyclerView(this)
        val adapter = BaseRecyclerAdapter(this, R.layout.item_rv)
        adapter.addBindViewHelper { holder, position ->
            val str = "数据 $position"
            holder.itemView.findViewById<TextView>(R.id.item_tv).text = str
        }
        adapter.addItemClick(R.id.item_tv, fun(v: View, p: Int) {
            ToastUtil.showShortToast(this, "" + p)
        })
        for (i in 0..40) adapter.addData(HashMap())
        recyclerRv.adapter = adapter
        recyclerRv.layoutManager = LinearLayoutManager(this)

        bottomSheetDialog = BottomSheetDialog(this).apply {
            setContentView(recyclerRv)
        }
    }

    override fun setDisplayHomeAsUpEnabled(): Boolean {
        return true
    }
}