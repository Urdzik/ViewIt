package com.company.archapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmark
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import java.util.*
import kotlin.collections.ArrayList

class ResultActivity : AppCompatActivity() {

    private val slidingPanelLayout by lazy { findViewById<SlidingUpPanelLayout>(R.id.sliding_panel)!! }
    private val landmarkIv by lazy { findViewById<ImageView>(R.id.landmark_iv) }
    private val landmarkTv by lazy { findViewById<TextView>(R.id.landmark_tv) }
    private val textPut by lazy { findViewById<TextView>(R.id.urltext) }
    private val imagePut by lazy { findViewById<ImageView>(R.id.info_image) }
    private val resultPb by lazy { findViewById<ProgressBar>(R.id.result_pb) }
    private val informationTv by lazy { findViewById<TextView>(R.id.information_tv) }
    private val photosDSV by lazy { findViewById<DiscreteScrollView>(R.id.photos_dsv) }
    private val wk = WikipediaClass()
    private val iF = ImageActivity()
    private var nameOfLandmark: String? = null // Name of recognized landmark
    private var latitude: Double? = null // Latitude of recognized landmark
    private var longitude: Double? = null // Longitude of recognized landmark

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Find the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        //Button backwards
        Objects.requireNonNull<ActionBar>(supportActionBar).setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        // Get image from intent
        val intent = intent
        val imageUri = intent.getParcelableExtra<Uri>(WelcomeActivity.IMAGE_URI)

        // Analyze our image
        analyzeImage(MediaStore.Images.Media.getBitmap(contentResolver, imageUri))

        // Add to photosDSV adapter and simple animation transformer
        val adapter = PhotosAdapter(generateData(), contentResolver)
        photosDSV.adapter = adapter
        photosDSV.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.05f) // min scale
                .setMinScale(0.8f) // max scale
                .setPivotY(Pivot.Y.BOTTOM) // position
                .build()
        )
    }

    // Find the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SetTextI18n")
    private fun analyzeImage(image: Bitmap?) {
        if (image == null) {
            // If no image we show Toast about error
            Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
            return
        }

        // Delete an image from the screen and show the progress Bar
        landmarkIv.setImageBitmap(null)

        showProgress()

        // Preparation for processing image
        val firebaseVisionImage = FirebaseVisionImage.fromBitmap(image)
        val options = FirebaseVisionCloudDetectorOptions.Builder()
            .setMaxResults(5)
            .build()
        val landmarkDetector = FirebaseVision.getInstance().getVisionCloudLandmarkDetector(options)

        // Detect the image
        landmarkDetector.detectInImage(firebaseVisionImage)
            .addOnSuccessListener {
                // We convert the image into a bitmap image in order to display the image on the screen
                val mutableImage = image.copy(Bitmap.Config.ARGB_8888, true)

                // Recognize landmarks
                recognizeLandmarks(it, mutableImage)

                // Set our image, hide the ProgressBar and show the recognized landmark

                landmarkIv.setImageBitmap(mutableImage)
                if (nameOfLandmark != null) {
                    landmarkTv.text = nameOfLandmark

                    wk.findWikipediaText(nameOfLandmark, informationTv, resultPb, slidingPanelLayout)

                    iF.putNameOfLandmarkToImage(nameOfLandmark, imagePut, textPut)

                } else {
                    landmarkTv.text = "Landmark not recognized"
                    hideProgress()
                }
            }
            .addOnFailureListener {
                // If we got error show a Toast about error
                Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
                hideProgress()
            }
    }

    private fun recognizeLandmarks(landmarks: List<FirebaseVisionCloudLandmark>?, image: Bitmap?) {
        if (landmarks == null || image == null) {
            // If no image or no landmarks show a Toast about error
            Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
            return
        }

        // Get landmarks
        for (landmark in landmarks) {

            nameOfLandmark = landmark.landmark

            // Get locations landmarks
            for (loc in landmark.locations) {
                latitude = loc.latitude
                longitude = loc.longitude
            }
        }
    }

    private fun showProgress() {
        /** Show progressbar */
        slidingPanelLayout.visibility = View.GONE
        resultPb.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        /** Hide progressbar */
        slidingPanelLayout.visibility = View.VISIBLE
        resultPb.visibility = View.GONE
    }

    private fun generateData(): List<PhotoItem> {
        val photos = ArrayList<PhotoItem>()
        photos.add(PhotoItem(intent.getParcelableExtra<Uri>(WelcomeActivity.IMAGE_URI).toString()))
        photos.add(PhotoItem(intent.getParcelableExtra<Uri>(WelcomeActivity.IMAGE_URI).toString()))
        photos.add(PhotoItem(intent.getParcelableExtra<Uri>(WelcomeActivity.IMAGE_URI).toString()))
        photos.add(PhotoItem(intent.getParcelableExtra<Uri>(WelcomeActivity.IMAGE_URI).toString()))
        photos.add(PhotoItem(intent.getParcelableExtra<Uri>(WelcomeActivity.IMAGE_URI).toString()))
        photos.add(PhotoItem(intent.getParcelableExtra<Uri>(WelcomeActivity.IMAGE_URI).toString()))
        return photos
    }
}

