package com.example.nonu.behaviordemo.util

import android.content.Context
import android.widget.Toast

/**
 * <pre>
 *     author : Nonu
 *     e-mail : 1162365377@qq.com
 *     time   : 2018/5/5
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ToastUtil {

    companion object {
        private var sToast: Toast? = null

        fun showShortToast(context: Context, text: CharSequence) {
            sToast?.cancel()
            sToast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
            sToast!!.show()
        }

        fun showLongToast(context: Context, text: CharSequence) {
            sToast?.cancel()
            sToast = Toast.makeText(context, text, Toast.LENGTH_LONG)
            sToast!!.show()
        }
    }
}