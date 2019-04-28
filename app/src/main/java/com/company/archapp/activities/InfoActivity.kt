package com.company.archapp.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.company.archapp.R
import com.company.archapp.activities.savedlandmarksactivity.SavedLandmarksActivity

class InfoActivity : AppCompatActivity() {

    private lateinit var tp: Typeface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val textView: TextView = findViewById(R.id.authors)
        val dev1: TextView = findViewById(R.id.dev1)
        val dev2: TextView = findViewById(R.id.dev2)
        val dev3: TextView = findViewById(R.id.dev3)
        val dev4: TextView = findViewById(R.id.dev4)

        tp = Typeface.createFromAsset(assets, "fonts/ProductSans-Bold.ttf")
        textView.typeface = tp
        dev1.typeface = tp
        dev2.typeface = tp
        dev3.typeface = tp
        dev4.typeface = tp

        // Find the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Button backwards
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    // Find the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_info, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.saved_landmarks -> {
                    startActivity(Intent(this@InfoActivity, SavedLandmarksActivity::class.java))
                    return true
                }
            }
        }
        return true
    }

}
