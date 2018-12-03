package com.adrian.indicatorsample

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs
import kotlin.math.max

/**
 * date:2018/11/30 9:47
 * author：RanQing
 * description：
 */
class ZoomOutPageTransformer : ViewPager.PageTransformer {

    companion object {
        const val MIN_SCALE = .9f
        const val MIN_ALPHA = .5f
    }

    override fun transformPage(p0: View, p1: Float) {
        val pageW = p0.width
        val pageH = p0.height

        when {
            p1 < -1 -> //向左滑出屏幕
                p0.alpha = 0f
            p1 <= 1 -> {
                val scaleFactor = max(MIN_SCALE, 1 - abs(p1))
                val vMargin = pageH * (1 - scaleFactor) / 2
                val hMargin = pageW * (1 - scaleFactor) / 2
                if (p1 < 0) {
                    p0.translationX = hMargin - vMargin / 2
                } else {
                    p0.translationX = -hMargin + vMargin / 2
                }

                p0.scaleX = scaleFactor
                p0.scaleY = scaleFactor

                p0.alpha = MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)
            }
            else -> p0.alpha = 0f
        }
    }
}