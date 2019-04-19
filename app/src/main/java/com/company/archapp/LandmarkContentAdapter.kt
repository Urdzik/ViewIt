package com.company.archapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions


/**
 * Discrete scroll view adapter
 */
class LandmarkContentAdapter(
    private val data: List<LandmarkContentItem>?,
    private val context: Context
) : RecyclerView.Adapter<LandmarkContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId =
            if (viewType == LandmarkContentItem.VIEW_IMAGE) R.layout.landmark_content_photo else R.layout.landmark_content_map

        val inflatedView = LayoutInflater.from(context)
            .inflate(layoutId, parent, false)

        return ViewHolder(inflatedView, context)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)

        val item = holder.item

        if (item != null && item.viewType == LandmarkContentItem.VIEW_MAP) {
            holder.getMapFragmentAndCallBack(OnMapReadyCallback {
                val latLng = item.latLng
                it.addMarker(MarkerOptions().position(latLng))
                it.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            })
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)

        if (holder.item != null && holder.item?.viewType == LandmarkContentItem.VIEW_MAP) {
            // If error still occur unpredictably, it's best to remove fragment here
            // holder.removeMapFragment();
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Getting image from data list and setting to image
        val item = data?.get(position)

        if (item?.viewType == LandmarkContentItem.VIEW_IMAGE) {
            holder.photoIv?.let {
                Glide.with(context)
                    .load(item.photoURI)
                    .into(it)
            }
        } else if (item?.viewType == LandmarkContentItem.VIEW_MAP) {
            holder.item = item
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun getItemViewType(position: Int): Int = data?.get(position)?.viewType ?: 0

    class ViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        val photoIv: ImageView? by lazy { itemView.findViewById<ImageView>(R.id.photo_iv) }
        val mapLayout by lazy { itemView.findViewById<FrameLayout>(R.id.map) }
        private var mapFragment: SupportMapFragment? = null
        var item: LandmarkContentItem? = null

        fun getMapFragmentAndCallBack(callback: OnMapReadyCallback): SupportMapFragment? {
            if (mapFragment == null) {
                mapFragment = SupportMapFragment.newInstance()
                mapFragment?.getMapAsync(callback)
            }

            mapFragment?.let {
                (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(
                    R.id.map,
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
