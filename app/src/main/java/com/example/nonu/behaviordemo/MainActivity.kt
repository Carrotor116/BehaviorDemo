package com.example.nonu.behaviordemo

import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import com.example.nonu.behaviordemo.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        bottom_sheet_btn.setOnClickListener { startActivity(Intent(this, BottomBehaviorActivity::class.java)) }
        fat_up_show_btn.setOnClickListener { startActivity(Intent(this, FatDownShowActivity::class.java)) }
        emu_zhihu_btn.setOnClickListener { startActivity(Intent(this, EmuZhihuActivity::class.java)) }
    }

    override fun initData() {

    }

    override fun setDisplayHomeAsUpEnabled(): Boolean {
        return false
    }
}
