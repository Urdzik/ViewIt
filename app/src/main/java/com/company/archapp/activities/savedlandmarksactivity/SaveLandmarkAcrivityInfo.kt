package com.company.archapp.activities.savedlandmarksactivity

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.company.archapp.R

class SaveLandmarkAcrivityInfo : AppCompatActivity() {

    private val wikiInfoBt by lazy { findViewById<Button>(R.id.read_more_btn) }
    private lateinit var tp: Typeface // Font | Шрифт

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_landmark)

        // Font button browser wiki
        // Шрифт для button browser wiki
        tp = Typeface.createFromAsset(assets, "fonts/ProductSans-Bold.ttf")
        wikiInfoBt.typeface = tp

    }
}