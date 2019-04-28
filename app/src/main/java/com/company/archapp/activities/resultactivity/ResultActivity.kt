package com.company.archapp.activities.resultactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.chahinem.pageindicator.PageIndicator
import com.company.archapp.R
import com.company.archapp.WikipediaClass
import com.company.archapp.activities.InfoActivity
import com.company.archapp.activities.WelcomeActivity
import com.company.archapp.activities.savedlandmarksactivity.SavedLandmarksActivity
import com.company.archapp.image.ImagesFromEthernet
import com.company.archapp.models.Landmark
import com.company.archapp.models.Photo
import com.company.archapp.models.Position
import com.company.archapp.models.WikiArticle
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmark
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import io.realm.Realm

class ResultActivity : AppCompatActivity() {

    private val slidingPanelLayout by lazy { findViewById<SlidingUpPanelLayout>(R.id.sliding_panel) }
    private val landmarkIv by lazy { findViewById<ImageView>(R.id.landmark_iv) }
    private val landmarkTv by lazy { findViewById<TextView>(R.id.landmark_tv) }
    private val resultPb by lazy { findViewById<ProgressBar>(R.id.result_pb) }
    private val informationTv by lazy { findViewById<TextView>(R.id.information_tv) }
    private val landmarkContentDSV by lazy { findViewById<DiscreteScrollView>(R.id.landmark_content_dsv) }
    private val dotsPi by lazy { findViewById<PageIndicator>(R.id.dots) }
    private val wikiInfoBt by lazy { findViewById<Button>(R.id.wiki_site_bt) }
    private val realm by lazy { Realm.getDefaultInstance() }
    private val wk = WikipediaClass()
    private val iF = ImagesFromEthernet()
    private var nameOfLandmark: String? = null // Name of recognized landmark
    private var latitude: Double? = null // Latitude of recognized landmark
    private var longitude: Double? = null // Longitude of recognized landmark
    private lateinit var photosUrls: Array<out String>
    private lateinit var tp: Typeface
    private lateinit var information: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Font button browser wiki
        tp = Typeface.createFromAsset(assets, "fonts/ProductSans-Bold.ttf")
        wikiInfoBt.typeface = tp

        // Find the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        //Button backwards
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        // Add simple transformer to DSV
        landmarkContentDSV.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.05f) // min scale
                .setMinScale(0.8f) // max scale
                .build()
        )

        // Get image from intent
        val intent = intent
        val imageUri = intent.getParcelableExtra<Uri>(WelcomeActivity.IMAGE_URI)

        // Analyze our image
        analyzeImage(MediaStore.Images.Media.getBitmap(contentResolver, imageUri))

        // Button browser wiki
        wikiInfoBt.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/$nameOfLandmark"))
            startActivity(browserIntent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    // Find the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_button_save, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.saved_landmarks -> {
                    startActivity(Intent(this@ResultActivity, SavedLandmarksActivity::class.java))
                    return true
                }

                R.id.info -> {
                    startActivity(Intent(this@ResultActivity, InfoActivity::class.java))
                    return true
                }
                R.id.save -> {
                    saveLandmarkToDatabase()
                    return true
                }
            }
        }
        return true
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

                    information = wk.findWikipediaText(nameOfLandmark)
                    informationTv.text = information

                    photosUrls = iF.putNameOfLandmarkToImage(nameOfLandmark)
                    generateDataForDSV()

                    hideProgress()
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
        landmarks.forEach { landmark ->
            nameOfLandmark = landmark.landmark

            // Get locations landmarks
            landmark.locations.forEach { latLng ->
                latitude = latLng.latitude
                longitude = latLng.longitude
            }
        }
    }

    private fun saveLandmarkToDatabase() {

        if (realm.where(Landmark::class.java).equalTo("name", nameOfLandmark).findFirst() == null) {
            // If the user has not previously saved this landmark, save it
            realm.executeTransaction {
                val article = realm.createObject(WikiArticle::class.java)
                article.article = information
                article.uri = "https://en.wikipedia.org/wiki/$nameOfLandmark"

                val position = realm.createObject(Position::class.java)
                position.latitude = latitude ?: 0.0
                position.longitude = longitude ?: 0.0

                val photo = realm.createObject(Photo::class.java)
                photo.uri = photosUrls[0]

                val landmark = realm.createObject(Landmark::class.java)
                landmark.name = nameOfLandmark.toString()
                landmark.article = article
                landmark.photo = photo
                landmark.position = position
            }
            Toast.makeText(this, "Landmark Saved", Toast.LENGTH_SHORT).show()
        } else {
            // Otherwise, let the user know, then he previously saved this landmark.
            Toast.makeText(this, "Sorry, but you have previously saved this landmark.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateDataForDSV() {

        val landmarkContentItems = ArrayList<LandmarkContentItem>()

        landmarkContentItems.add(
            LandmarkContentItem(
                LatLng(
                    latitude ?: 0.0, longitude ?: 0.0
                )
            )
        )
        photosUrls.forEach {
            landmarkContentItems.add(LandmarkContentItem(it))
        }
        val adapter = LandmarkContentAdapter(landmarkContentItems, this)
        landmarkContentDSV.adapter = adapter

        dotsPi.attachTo(landmarkContentDSV)
    }

    override fun onBackPressed() {
        if (slidingPanelLayout != null &&
            (slidingPanelLayout.panelState == SlidingUpPanelLayout.PanelState.EXPANDED ||
                    slidingPanelLayout.panelState == SlidingUpPanelLayout.PanelState.ANCHORED)
        ) {
            slidingPanelLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        } else {
            super.onBackPressed()
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
}

