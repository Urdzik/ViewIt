package com.company.archapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var slidingPanelLayout: SlidingUpPanelLayout
    private lateinit var landmarkIv: ImageView
    private lateinit var dragview: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init views
        slidingPanelLayout = findViewById<SlidingUpPanelLayout>(R.id.sliding_panel)
        landmarkIv = findViewById<ImageView>(R.id.landmark_iv)
        dragview = findViewById(R.id.dragview)

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

        slidingPanelLayout.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View, slideOffset: Float) {
                // Nothing to do here
            }

            override fun onPanelStateChanged(
                panel: View,
                previousState: SlidingUpPanelLayout.PanelState,
                newState: SlidingUpPanelLayout.PanelState
            ) {
                // When sliding panel transform to EXPANDED state, we remove radius from background
                if (newState == PanelState.EXPANDED) {
                    dragview.background = getDrawable(R.color.silding_layout_color)
                } else {
                    // Otherwise we set to sliding panel radius
                    dragview.background = getDrawable(R.drawable.sliding_panel_radius)
                }
            }
        })
    }
}