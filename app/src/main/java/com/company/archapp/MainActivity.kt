package com.company.archapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState

class MainActivity : AppCompatActivity() {
    private lateinit var slidingPanelLayout: SlidingUpPanelLayout
    private lateinit var landmarkIv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init views
        slidingPanelLayout = findViewById<SlidingUpPanelLayout>(R.id.sliding_panel)
        landmarkIv = findViewById<ImageView>(R.id.landmark_iv)

        // When app starts we don't need to see sliding panel
        slidingPanelLayout.panelState = PanelState.HIDDEN

        landmarkIv.setOnClickListener {
            // When we click the image we must see sliding panel
            if (slidingPanelLayout.panelState == PanelState.HIDDEN) {
                slidingPanelLayout.panelState = PanelState.COLLAPSED
            } else {
                // Otherwise we hide the sliding panel
                slidingPanelLayout.panelState = PanelState.HIDDEN
            }
        }
    }
}