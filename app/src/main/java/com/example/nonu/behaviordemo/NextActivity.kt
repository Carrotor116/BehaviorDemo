package com.example.nonu.behaviordemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.example.nonu.behaviordemo.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.activity_next.*

/**
 * <pre>
 *     author : Nonu
 *     e-mail : 1162365377@qq.com
 *     time   : 2018/5/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        recycler_rv.layoutManager = LinearLayoutManager(this)
        val adapter = BaseRecyclerAdapter(this, R.layout.item_rv)
        adapter.addBindViewHelper { holder, position -> holder.itemView.findViewById<TextView>(R.id.item_tv).text = "数据" + position }
        adapter.addItemClick(R.id.item_tv, fun(_: View, _: Int) {
            recycler_rv.scrollToPosition(0)
        })
        for (i in 0..40) adapter.addData(HashMap())
        recycler_rv.adapter = adapter
    }
}