package com.company.archapp.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import com.company.archapp.R

class NoLandmark : AppCompatActivity() {
    private val btnNoLandmark by lazy { findViewById<Button>(R.id.try_again_btn_landmark) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_landmark)

        // Find the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        btnNoLandmark.setOnClickListener {
           startActivity(Intent(this@NoLandmark, WelcomeActivity::class.java))
        }

        val typeface = Typeface.createFromAsset(assets, "fonts/ProductSans-Bold.ttf")
        btnNoLandmark?.typeface = typeface

    }

    override fun onBackPressed() {
        startActivity(Intent(this, WelcomeActivity::class.java))
    }
}
