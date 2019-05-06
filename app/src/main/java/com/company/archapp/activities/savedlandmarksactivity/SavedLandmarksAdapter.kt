package com.company.archapp.activities.savedlandmarksactivity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.company.archapp.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SavedLandmarksAdapter(
    data: List<SavedLandmark>,
    private val context: Context
) : RecyclerView.Adapter<SavedLandmarksAdapter.ViewHolder>() {

    var data: List<SavedLandmark> = data
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.saved_landmark, parent, false)

        return ViewHolder(itemView, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.item = item

        holder.setMapLocation()

        Glide.with(context)
            .load(item.photo)
            .into(holder.photoIv)

        holder.nameTv.text = item.name

        holder.articleTv.text = item.article

        holder.readMoreBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.articleUrl))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView),
        OnMapReadyCallback {
        val photoIv: ImageView by lazy { itemView.findViewById<ImageView>(R.id.landmark_photo_iv) }
        val nameTv: TextView by lazy { itemView.findViewById<TextView>(R.id.landmark_name_tv) }
        val articleTv: TextView by lazy { itemView.findViewById<TextView>(R.id.landmark_article_tv) }
        val readMoreBtn: Button by lazy { itemView.findViewById<Button>(R.id.read_more_btn) }
        private val mapView: MapView by lazy { itemView.findViewById<MapView>(R.id.map_layout) }
        var item: SavedLandmark? = null
        var map: GoogleMap? = null

        init {
            // Init Map
            // Иницилизируем карту
            mapView.onCreate(null)
            mapView.getMapAsync(this)
        }

        override fun onMapReady(googleMap: GoogleMap) {
            MapsInitializer.initialize(context)
            map = googleMap
            setMapLocation()
        }

        fun setMapLocation() {
            map?.apply {
                moveCamera(CameraUpdateFactory.newLatLngZoom(item?.position, 15f))
                addMarker(MarkerOptions().position(item?.position ?: LatLng(0.0, 0.0)))
                mapType = GoogleMap.MAP_TYPE_NORMAL
            }
        }
    }
}