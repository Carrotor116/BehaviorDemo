package com.example.nonu.behaviordemo.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.example.nonu.behaviordemo.R
import kotlin.math.abs
import kotlin.math.sign

/**
 * <pre>
 *     author : Nonu
 *     e-mail : 1162365377@qq.com
 *     time   : 2018/5/29
 *     desc   : dependence 上滑滚动时隐藏 child，child采用下滑出屏方式隐藏。
 *              用例：主屏上滑显示更多内容时，隐藏其他view，扩大内容显示空间。
 *     version: 1.0
 * </pre>
 */

class ScrollUpHideBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {

    private var mTargetId: Int = -1
    private var mReverse = false

    private var childOriginTop = -1
    private var mHThreshold = 0f
    private var screenHeight = 0
    private var translationY = 0

    private var accumulativeDependenceHeight = 0
    private var hided = false

    companion object {
        private const val H_THRESHOLD_DEFAULT_DP = 30f // 单位dp
    }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.Follow)
        for (i in 0 until a.indexCount) {
            val attr = a.getIndex(i)
            when (attr) {
                R.styleable.Follow_behavior_target -> mTargetId = a.getResourceId(attr, -1)
                R.styleable.Follow_behavior_reverse -> mReverse = a.getBoolean(attr, false)
                R.styleable.Follow_behavior_scroll_threshold -> mHThreshold = a.getDimension(attr, H_THRESHOLD_DEFAULT_DP)
            }
        }
        a.recycle()
        Log.d("nonu", "target id: $mTargetId")
    }

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return dependency?.id == mTargetId
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        Log.d("nonu", "accumulativeDependenceHeight : $accumulativeDependenceHeight , mHThreshold : $mHThreshold, dy: $dyConsumed, hided: $hided, child.top: ${child.top}")
        if (scrolling) return // 当正在滑动的时候，不再滑动，防止位移异常
        if ((!hided && dyConsumed > 0) || (hided && dyConsumed < 0)) {
            accumulativeDependenceHeight += dyConsumed
        }
        if (abs(accumulativeDependenceHeight) > mHThreshold) {
            val direction = if (mReverse) -1 else 1
            if ((accumulativeDependenceHeight > 0 && !hided) || (accumulativeDependenceHeight < 0 && hided)) {
                ViewCompat.animate(child)
                        .translationYBy(translationY * sign(accumulativeDependenceHeight.toFloat()) * direction)
                        .setDuration(400)
                        .setListener(listener)
                        .start()
                hided = accumulativeDependenceHeight.toFloat() > 0
                Log.d("nonu", "changed hided: $hided")
                accumulativeDependenceHeight = 0
            }
        }
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }

    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: View, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        if (scrolling) return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
        val direction = if (mReverse) -1 else 1
        if ((velocityY > 0 && !hided) || (velocityY < 0 && hided)) {
            ViewCompat.animate(child)
                    .translationYBy(translationY * sign(velocityY) * direction)
                    .setDuration(400)
                    .setListener(listener)
                    .start()
            hided = velocityY > 0
            Log.d("nonu", "changed hided: $hided")
            accumulativeDependenceHeight = 0
        }
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        if (screenHeight == 0) {
            screenHeight = child.context.resources.displayMetrics.heightPixels
            if (mHThreshold <= 0) {
                mHThreshold = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, H_THRESHOLD_DEFAULT_DP, child.context.resources.displayMetrics)
            }
            if (!mReverse) {
                childOriginTop = child.top
                translationY = abs(child.top - screenHeight)
            } else {
                childOriginTop = child.bottom
                translationY = abs(child.bottom)
            }
        }
        return target.id == mTargetId
    }

    private var scrolling = false
    private val listener = object : ViewPropertyAnimatorListener {
        override fun onAnimationEnd(view: View?) {
            scrolling = false
        }

        override fun onAnimationCancel(view: View?) {
            scrolling = false
        }

        override fun onAnimationStart(view: View?) {
            scrolling = true
        }
    }

}