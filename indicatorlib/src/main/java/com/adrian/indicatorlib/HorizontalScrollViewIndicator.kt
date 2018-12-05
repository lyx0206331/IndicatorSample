package com.adrian.indicatorlib

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import kotlin.math.log

/**
 * date:2018/12/5 16:48
 * author：RanQing
 * description：
 */
class HorScrollViewIndicator @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    companion object {
        const val COLOR_BACKGROUND = Color.GRAY
        const val COLOR_PROGRESS = Color.CYAN
    }

    private val bgPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }
    private val barPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }
    private var mScrollX = 0
    private var mScrollViewW = 0
    var mHorScrollView: HorScrollView? = null
        set(value) {
            field = value
            mScrollViewW = value?.width.orZero()
            value?.scrollViewListener = object : ScrollViewListener {
                override fun onScrollChanged(scrollView: HorScrollView, x: Int, y: Int, oldX: Int, oldY: Int) {
//                    logE("scroll x:$x, y:$y, oldx:$oldX, oldy:$oldY")
                    mScrollX = x
                    invalidate()
                }
            }
        }
    var mBgColor: Int = COLOR_BACKGROUND
        set(value) {
            field = value
            bgPaint.color = value
        }
    var mBarColor: Int = COLOR_PROGRESS
        set(value) {
            field = value
            barPaint.color = value
        }
    var mRadius = 5f
    var mBarWidth = 0

    init {
        bgPaint.color = mBgColor
        barPaint.color = mBarColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val width = if (widthMode == MeasureSpec.EXACTLY) widthSize else widthSize.shr(1)
        val height = if (heightMode == MeasureSpec.EXACTLY) heightSize else heightSize.shr(1)

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mBarWidth = if (mBarWidth <= 0) width / 2 else mBarWidth
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val bgRectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
        val offsetX = 1f * (width - mBarWidth) * mScrollX / (mScrollViewW - width)
        logE("offsetX:$offsetX, (width-barW):${width - mBarWidth}, width:$width")
        val barRectF = RectF(offsetX, 0f, offsetX + mBarWidth, height.toFloat())
        canvas?.drawRoundRect(bgRectF, mRadius, mRadius, bgPaint)
        canvas?.drawRoundRect(barRectF, mRadius, mRadius, barPaint)
    }
}

class HorScrollView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    HorizontalScrollView(context, attrs, defStyleAttr) {

    var scrollViewListener: ScrollViewListener? = null

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        scrollViewListener?.onScrollChanged(this, l, t, oldl, oldt)
    }
}

interface ScrollViewListener {
    fun onScrollChanged(scrollView: HorScrollView, x: Int, y: Int, oldX: Int, oldY: Int)
}

fun logE(msg: String) {
    Log.e("SCROLLVIEW", msg)
}