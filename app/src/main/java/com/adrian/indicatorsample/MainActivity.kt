package com.adrian.indicatorsample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        viewPager.adapter = ViewPagerAdapter()
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())

        dotsIndicator.mViewPager = viewPager
//        dotsIndicator.dotsClickable = false
        dotsIndicator.dotsColor = Color.CYAN
    }
}
