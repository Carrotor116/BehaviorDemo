package com.example.nonu.behaviordemo.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.nonu.behaviordemo.R

/**
 * <pre>
 *     author : Nonu
 *     e-mail : 1162365377@qq.com
 *     time   : 2018/5/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        initToolBar()
        initData()
        initView()
    }

    abstract fun getLayoutRes(): Int

    abstract fun initView()
    abstract fun initData()

    private fun initToolBar() {
        val view = findViewById<Toolbar?>(R.id.tool_bar)
        if (view != null) {
            setSupportActionBar(view)
            supportActionBar?.setDisplayHomeAsUpEnabled(setDisplayHomeAsUpEnabled())

        }
    }

    abstract fun setDisplayHomeAsUpEnabled(): Boolean

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}