package com.example.nonu.behaviordemo.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.nonu.behaviordemo.util.AnimatorUtil

/**
 * <pre>
 *     author : Nonu
 *     e-mail : 1162365377@qq.com
 *     time   : 2018/5/29
 *     desc   : dependence 上滑滚动时显示 child，下滑隐藏 child，采用缩放动画显示隐藏。用例：置顶fat
 *     version: 1.0
 * </pre>
 */

class ScrollUpShowBehavior(context: Context, attr: AttributeSet) : FloatingActionButton.Behavior(context, attr) {

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        Log.d("nonu", "dyConsumed:$dyConsumed dyUnconsumed$dyUnconsumed")
        if ((child.scaleX == 0f) && dyConsumed > 0) {// 下滑中 || 下滑到最底部
            Log.d("nonu", "下滑")
            ani = AnimatorUtil.scaleShow(child, null, 400)
        } else if (child.scaleX == 1f && dyConsumed < 0) {
            ani?.cancel()
            Log.d("nonu", "上滑")
            ani = AnimatorUtil.scaleHide(child, null, 400)
        }
    }

    private var ani: ViewPropertyAnimatorCompat? = null
}