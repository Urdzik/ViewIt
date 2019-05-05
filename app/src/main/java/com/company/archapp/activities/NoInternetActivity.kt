package com.company.archapp.activities

import android.content.Intent
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.company.archapp.R
import com.company.archapp.activities.savedlandmarksactivity.SavedLandmarksActivity

class NoInternetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)

        // Find the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        //Button backwards
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            startActivity(
                Intent(
                    this@NoInternetActivity,
                    WelcomeActivity::class.java
                )
            )
        }

        val tryAgainBtn = findViewById<Button>(R.id.try_again_btn)
        tryAgainBtn.setOnClickListener {
            if (isOnline()) startActivity(Intent(this@NoInternetActivity, WelcomeActivity::class.java))
        }

        val typeface = Typeface.createFromAsset(assets, "fonts/ProductSans-Bold.ttf")
        tryAgainBtn?.typeface = typeface

    }

    // Find the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.info -> {
                    startActivity(Intent(this@NoInternetActivity, InfoActivity::class.java))
                    return true
                }
                R.id.saved_landmarks -> {
                    startActivity(Intent(this@NoInternetActivity, SavedLandmarksActivity::class.java))
                    return true
                }
            }
        }
        return true
    }

    //Проверка или есть интернет
    private fun isOnline(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null
    }
}
