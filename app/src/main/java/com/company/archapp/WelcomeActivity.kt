package com.company.archapp

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.theartofdev.edmodo.cropper.CropImage

class WelcomeActivity : AppCompatActivity() {

    private val recognizeBtn by lazy { findViewById<Button>(R.id.recognize_btn)!! }
    private lateinit var tp: Typeface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        tp = Typeface.createFromAsset(assets, "fonts/ProductSans-Bold.ttf")
        recognizeBtn.typeface = tp

        // Find the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)


        // Here we call CropImageActivity for get image for recognize
        recognizeBtn.setOnClickListener {
            CropImage.activity().start(this)
        }
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
                    startActivity(Intent(this@WelcomeActivity, InfoActivity::class.java))
                    return true
                }
            }
        }
        return true
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // After CropImageActivity we got image for recognize and sending this image to ResultActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                // If result code is OK we start ResultActivity with image
                val resultUri = result.uri
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(IMAGE_URI, resultUri)
                startActivity(intent)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                // Else we Make Toast about error
                Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val IMAGE_URI = "image_uri"
    }
}
