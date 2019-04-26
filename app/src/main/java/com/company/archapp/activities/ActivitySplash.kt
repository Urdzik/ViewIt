package com.company.archapp.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.company.archapp.R

class ActivitySplash : AppCompatActivity() {
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        textView = findViewById(R.id.tv)

        val typeface = Typeface.createFromAsset(assets, "fonts/ProductSans-Bold.ttf")
        textView?.typeface = typeface

        //Code to start timer and take action after the timer ends
        Handler().postDelayed({
            //Do any action here. Now we are moving to next page
            val mySuperIntent = Intent(this@ActivitySplash, WelcomeActivity::class.java)
            startActivity(mySuperIntent)
            /* This 'finish()' is for exiting the app when back button pressed
                 *  from Home page which is ActivityHome
                 */
            finish()
        }, SPLASH_TIME.toLong())
    }

    companion object {
        private const val SPLASH_TIME = 2000 //This is 4 seconds
    }
}
