package com.example.nonu.behaviordemo.util

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View

/**
 * <pre>
 *     author : Nonu
 *     e-mail : 1162365377@qq.com
 *     time   : 2018/5/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class AnimatorUtil {
    companion object {
        fun scaleShow(v: View, listener: ViewPropertyAnimatorListener?, duration: Long = 800): ViewPropertyAnimatorCompat {
            v.visibility = View.VISIBLE
            v.scaleX = 0f
            v.scaleY = 0f

            val ani = ViewCompat.animate(v)
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(listener)
                    .setInterpolator(FastOutSlowInInterpolator())
            ani.start()
            return ani
        }


        fun scaleHide(v: View, listener: ViewPropertyAnimatorListener?, duration: Long = 800): ViewPropertyAnimatorCompat {
            v.scaleX = 1f
            v.scaleY = 1f
            val ani = ViewCompat.animate(v)
                    .scaleX(0f)
                    .scaleY(0f)
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(listener)
                    .setInterpolator(FastOutSlowInInterpolator())
            ani.start()
            return ani
        }
    }
}