package com.company.archapp.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.company.archapp.R
import com.company.archapp.activities.savedlandmarksactivity.SavedLandmarksActivity

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

    // Find the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.saved_landmarks -> {
                    startActivity(Intent(this@NoLandmark, SavedLandmarksActivity::class.java))
                    return true
                }
                R.id.info -> {
                    startActivity(Intent(this@NoLandmark, InfoActivity::class.java))
                    return true
                }
            }
        }
        return true
    }

    override fun onBackPressed() {
        startActivity(Intent(this, WelcomeActivity::class.java))
    }
}
