package com.company.archapp.activities.savedlandmarksactivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.company.archapp.R
import com.company.archapp.activities.InfoActivity
import com.company.archapp.models.Landmark
import com.google.android.gms.maps.model.LatLng
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

class SavedLandmarksActivity : AppCompatActivity() {

    private val savedLandmarksRv by lazy { findViewById<RecyclerView>(R.id.saved_landmarks_rv) }
    private val realm by lazy { Realm.getDefaultInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_landmarks)

        // Find the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        //Button backwards
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        savedLandmarksRv.apply {
            layoutManager = LinearLayoutManager(this@SavedLandmarksActivity, LinearLayoutManager.HORIZONTAL, false)

            adapter = SavedLandmarksAdapter(
                generateData(),
                this@SavedLandmarksActivity
            )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.saved_landmarks -> {
                    startActivity(Intent(this@SavedLandmarksActivity, SavedLandmarksActivity::class.java))
                    return true
                }

                R.id.info -> {
                    startActivity(Intent(this@SavedLandmarksActivity, InfoActivity::class.java))
                    return true
                }
            }
        }
        return true
    }

    /**
     * Generate data for recyclerview
     */
    private fun generateData(): List<SavedLandmarksItem> {
        val dataFromDatabase = loadData()
        val dataForRecyclerViewAdapter = ArrayList<SavedLandmarksItem>()

        dataFromDatabase?.forEach {
            dataForRecyclerViewAdapter.add(
                SavedLandmarksItem(
                    it.name,
                    it.article?.article.toString(),
                    it.article?.uri.toString(),
                    it.photo?.uri.toString(),
                    LatLng(it.position?.latitude ?: 0.0, it.position?.longitude ?: 0.0)
                )
            )
        }

        return dataForRecyclerViewAdapter
    }

    /**
     * Load data from realm database
     */
    private fun loadData(): RealmResults<Landmark>? {
        var landmarks: RealmResults<Landmark>? = null

        realm.executeTransaction {
            landmarks = it.where(Landmark::class.java).findAll()
        }

        return landmarks
    }
}
