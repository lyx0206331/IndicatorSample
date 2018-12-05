package com.adrian.indicatorsample.scrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adrian.indicatorsample.R
import kotlinx.android.synthetic.main.activity_hor_scroll_view.*

class HorScrollViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hor_scroll_view)

        horScrollViewIndicator.mHorScrollView = horScrollView
    }
}
