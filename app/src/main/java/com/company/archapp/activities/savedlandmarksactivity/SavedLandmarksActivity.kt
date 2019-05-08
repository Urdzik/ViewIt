package com.company.archapp.activities.savedlandmarksactivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.chahinem.pageindicator.PageIndicator
import com.company.archapp.R
import com.company.archapp.activities.InfoActivity
import com.company.archapp.activities.NoLandmark
import com.company.archapp.models.Landmark
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import io.realm.Realm
import io.realm.RealmResults

class SavedLandmarksActivity : AppCompatActivity() {

    private val savedLandmarksDsv by lazy { findViewById<DiscreteScrollView>(R.id.saved_landmarks_dsv) }
    private val dotsPi by lazy { findViewById<PageIndicator>(R.id.dots) }
    private val realm by lazy { Realm.getDefaultInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_landmarks)

        if (!hasAtLeastOneLandmark()) {
            val intent = Intent(this, NoLandmark::class.java)
            startActivity(intent)
        }

        // Find the toolbar
        // Находим туллбар
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        // Button backwards
        // Кнопка назад
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        // Setting up savedLandmarksDsv
        // Настраиваем savedLandmarksDsv
        savedLandmarksDsv.apply {
            setItemTransformer(
                ScaleTransformer.Builder()
                    .setMaxScale(1.05f)
                    .setMinScale(0.85f)
                    .build()
            )

            adapter = SavedLandmarksAdapter(
                generateData(),
                this@SavedLandmarksActivity
            )

            setRecyclerListener {
                val mapHolder = it as SavedLandmarksAdapter.ViewHolder
                mapHolder.map?.apply {
                    clear()
                    mapType = GoogleMap.MAP_TYPE_NONE
                }
            }
        }

        dotsPi.attachTo(savedLandmarksDsv)
    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.delete_landmark -> {
                    deleteLandmark()
                }
                R.id.info -> {
                    startActivity(Intent(this@SavedLandmarksActivity, InfoActivity::class.java))
                    return true
                }
            }
        }
        return true
    }

    private fun deleteLandmark() {
        val data =
            (savedLandmarksDsv.adapter as SavedLandmarksAdapter).data as ArrayList // Getting whole data from DiscreteScrollView adapter | Получаем данные из  DiscreteScrollView adapter
        val landmarkToDelete =
            data[savedLandmarksDsv.currentItem] // Getting current item from data | Получаем текущий елемент

        // Delete current item in realm DB
        // Удаляем текущий елемент из БД
        realm.executeTransaction {
            val toDelete = it.where(Landmark::class.java).equalTo("name", landmarkToDelete.name).findFirst()
            toDelete?.article?.deleteFromRealm()
            toDelete?.position?.deleteFromRealm()
            toDelete?.photo?.deleteFromRealm()
            toDelete?.deleteFromRealm()
        }

        // Update data
        // Обновляем данные

        ((savedLandmarksDsv.adapter as SavedLandmarksAdapter).data as ArrayList).removeAt(savedLandmarksDsv.currentItem)
        (savedLandmarksDsv.adapter as SavedLandmarksAdapter).notifyItemRemoved(savedLandmarksDsv.currentItem)

        dotsPi.attachTo(savedLandmarksDsv)

        Toast.makeText(
            this,
            "Landmark deleted.",
            Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * Generate data for DiscreteScrollView
     * Генерируем данные для DiscreteScrollView
     */
    private fun generateData(): List<SavedLandmark> {
        val dataFromDatabase = loadData()
        val dataForDiscreteScrollView = ArrayList<SavedLandmark>()

        dataFromDatabase?.forEach {
            dataForDiscreteScrollView.add(
                SavedLandmark(
                    it.name,
                    it.article?.article.toString(),
                    it.article?.uri.toString(),
                    it.photo?.uri.toString(),
                    LatLng(it.position?.latitude ?: 0.0, it.position?.longitude ?: 0.0)
                )
            )
        }

        return dataForDiscreteScrollView
    }

    private fun hasAtLeastOneLandmark(): Boolean {
        val count = loadData()?.size ?: 0
        return count > 0
    }

    /**
     * Load data from realm database
     * Загружаем данные из базы данных
     */
    private fun loadData(): RealmResults<Landmark>? {
        var landmarks: RealmResults<Landmark>? = null

        realm.executeTransaction {
            landmarks = it.where(Landmark::class.java).findAll()
        }

        return landmarks
    }
}
