package com.company.archapp.activities.resultactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Typeface
import android.net.ConnectivityManager
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
import com.company.archapp.activities.NoInternetActivity
import com.company.archapp.activities.NoLandmark
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
    private var nameOfLandmark: String? = null // Name of recognized landmark | Имя распознаной достопримечательности
    private var latitude: Double? = null // Latitude of recognized landmark | Широта распознаной достопримечательности
    private var longitude: Double? =
        null // Longitude of recognized landmark | Долгота распознаной достопримечательности
    private lateinit var photosUrls: Array<out String> // Array of urls of photos | Список ссылок картинок
    private lateinit var tp: Typeface // Font | Шрифт
    private lateinit var information: String // Article from Wikipedia | Статья из Википедии

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Font button browser wiki
        // Шрифт для button browser wiki
        tp = Typeface.createFromAsset(assets, "fonts/ProductSans-Bold.ttf")
        wikiInfoBt.typeface = tp

        // Find the toolbar
        // Находим тулбар
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        // Button backwards
        // Кнопка назад
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        // Add simple transformer to DiscreteScrollView
        // Добавим простенькую анимацию трансформации для DiscreteScrollView
        landmarkContentDSV.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.05f) // max scale | Максимальное увеличение
                .setMinScale(0.8f) // min scale | Минимально увеличени
                .build()
        )

        // Get image from intent
        // Получаем картинку с интента
        val intent = intent
        val imageUri = intent.getParcelableExtra<Uri>(WelcomeActivity.IMAGE_URI)

        // Analyze our image
        // Анализируем нашу картинку
        analyzeImage(MediaStore.Images.Media.getBitmap(contentResolver, imageUri))

        // Click Listeners for buttons
        // Клик листенеры для кнопок
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
    // Находим меню
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
            // Если нет картинки мы показываем Тост об ошибки
            Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
            return
        }

        // Delete an image from the screen and show the progress Bar
        // Удаляем картинку с экрана и показываем ПрогрессБар
        landmarkIv.setImageBitmap(null)

        showProgress()

        // Preparation for processing image
        // Подготовка к распознованию картинки
        val firebaseVisionImage = FirebaseVisionImage.fromBitmap(image)
        val options = FirebaseVisionCloudDetectorOptions.Builder()
            .setMaxResults(5)
            .build()
        val landmarkDetector = FirebaseVision.getInstance().getVisionCloudLandmarkDetector(options)

        // Detect the image
        // Распознаем картинку
        landmarkDetector.detectInImage(firebaseVisionImage)
            .addOnSuccessListener {

                // We convert the image into a bitmap image in order to display the image on the screen
                // Мы сонвертируем картинку в Битмапу для отображения на экране
                val mutableImage = image.copy(Bitmap.Config.ARGB_8888, true)

                // Recognize landmarks
                // Распознаем Достопримечательности
                recognizeLandmarks(it)


                if (nameOfLandmark != null) {
                    if (isOnline()) {

                       // Set our image, hide the ProgressBar and show the recognized landmark
                // Устонавлюем нашу картинку, прячем ПрогрессБар и показывм определённую дотопримечательность
                        landmarkIv.setImageBitmap(mutableImage)


                        landmarkTv.text = nameOfLandmark
                        if (isOnline()) information = wk.findWikipediaText(nameOfLandmark)
                        else startActivity(Intent(this, NoInternetActivity::class.java))
                        informationTv.text = information

                        if(isOnline()) photosUrls = iF.putNameOfLandmarkToImage(nameOfLandmark)
                        else startActivity(Intent(this, NoInternetActivity::class.java))

                        generateDataForDSV()

                        hideProgress()
                    } else {
                        startActivity(Intent(this, NoInternetActivity::class.java))
                    }
                } else {
                    slidingPanelLayout.visibility = View.GONE
                    startActivity(Intent(this@ResultActivity, NoLandmark::class.java))
                    hideProgress()
                }
            }
            .addOnFailureListener {
              
                // If we got error show a Activity about error
                // Если мы вдруг получили ошибку распознавания, то показываем экарн об Ошибке
                startActivity(Intent(this, NoInternetActivity::class.java))

                hideProgress()
            }
    }

    private fun recognizeLandmarks(landmarks: List<FirebaseVisionCloudLandmark>?) {
        if (landmarks == null) {
            // If no image or no landmarks show a Toast about error
            // Если нет достопримечательностей, то делаем Тост об Ошибке
            Toast.makeText(this, "There was some error", Toast.LENGTH_SHORT).show()
            return
        }

        // Get landmarks
        // Получаем достопримечательности
        landmarks.forEach { landmark ->
            nameOfLandmark = landmark.landmark

            // Get locations landmarks
            // Получаем Местоположение достопримечательностей
            landmark.locations.forEach { latLng ->
                latitude = latLng.latitude
                longitude = latLng.longitude
            }
        }
    }

    private fun saveLandmarkToDatabase() {

        if (realm.where(Landmark::class.java).equalTo("name", nameOfLandmark).findFirst() == null) {
            // If the user has not previously saved this landmark, save it
            // Если юзер ранее не сохранял даную достопримечательность, то сохраняем её
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
            // В ином случае, дадим юзеру знать, что он ранее сохранял эту достопримечательность
            Toast.makeText(this, "Sorry, but you have previously saved this landmark.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateDataForDSV() {
        // Generate data for DiscreteScrollView
        // Генерируем данные для DiscreteScrollView

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
            // If sliding panel is opened, close it
            // Если слайдинговая панель открыта, закрываем её
            slidingPanelLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        } else {
            super.onBackPressed()
        }
    }

    private fun showProgress() {
        /** Show progressbar
         * Показываем прогресс бар
         */
        slidingPanelLayout.visibility = View.GONE
        resultPb.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        /** Hide progressbar
         * Прячем прогресс бар
         */
        slidingPanelLayout.visibility = View.VISIBLE
        resultPb.visibility = View.GONE
    }


    //Проверка или есть интернет
    private fun isOnline(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null
    }
}

