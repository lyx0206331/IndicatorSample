package com.adrian.indicatorsample.viewpager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adrian.indicatorsample.R
import com.adrian.indicatorsample.scrollview.HorScrollViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
        setContentView(R.layout.activity_main)

        viewPager.adapter = ViewPagerAdapter()
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())

        dotsIndicator.mViewPager = viewPager
//        dotsIndicator.dotsClickable = false
//        dotsIndicator.dotsSelectedColor = Color.CYAN
        horDotsIndcatorAct.setOnClickListener {
            startActivity(Intent(this, HorScrollViewActivity::class.java))
        }
    }
}
