package com.company.archapp.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.company.archapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SavedLandmarksAdapter(
    private val data: List<SavedLandmarksItem>,
    private val context: Context
) : RecyclerView.Adapter<SavedLandmarksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.saved_landmarks_item, parent, false)

        return ViewHolder(itemView, context)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)

        val item = holder.item

        item.apply {
            holder.getMapFragmentAndCallBack(OnMapReadyCallback {
                val latLng = this?.position
                it.addMarker(MarkerOptions().position(this?.position ?: LatLng(0.0, 0.0)))
                it.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                it.setMinZoomPreference(15f)
            })
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)

        // If error still occur unpredictably, it's best to remove fragment here
        holder.removeMapFragment()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.item = item

        Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()

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

    class ViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        val photoIv: ImageView by lazy { itemView.findViewById<ImageView>(R.id.landmark_photo_iv) }
        val nameTv: TextView by lazy { itemView.findViewById<TextView>(R.id.landmark_name_tv) }
        val articleTv: TextView by lazy { itemView.findViewById<TextView>(R.id.landmark_article_tv) }
        val readMoreBtn: Button by lazy { itemView.findViewById<Button>(R.id.read_more_btn) }
        private var mapFragment: SupportMapFragment? = null
        var item: SavedLandmarksItem? = null

        fun getMapFragmentAndCallBack(callback: OnMapReadyCallback): SupportMapFragment? {
            if (mapFragment == null) {
                mapFragment = SupportMapFragment.newInstance()
                mapFragment?.getMapAsync(callback)
            }

            mapFragment?.let {
                (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(
                    R.id.map_layout,
                    it
                ).commit()
            }
            return mapFragment
        }

        fun removeMapFragment() {
            mapFragment?.let {
                (context as AppCompatActivity).supportFragmentManager.beginTransaction().remove(it)
                    .commitAllowingStateLoss()
            }

            mapFragment?.let { mapFragment = null }
        }
    }
}