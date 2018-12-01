package com.adrian.indicatorsample

import android.support.annotation.ColorRes
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * date:2018/11/30 11:03
 * author：RanQing
 * description：
 */
class ViewPagerAdapter : PagerAdapter() {

    val items = arrayListOf(
        Item(R.color.md_indigo_500),
        Item(R.color.md_green_500),
        Item(R.color.md_red_500),
        Item(R.color.md_orange_500),
        Item(R.color.md_purple_500)
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.material_page, container, false)
        container.addView(view)
        return view
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    class Item(@ColorRes colorRes: Int)
}