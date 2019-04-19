package com.company.archapp.image

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.company.archapp.R


/**
 * Discrete scroll view adapter
 */
class LandmarkContentAdapter(
    private val data: List<LandmarkContentItem>?,
    private val context: Context
) : RecyclerView.Adapter<LandmarkContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = R.layout.landmark_content_photo

        val inflatedView = LayoutInflater.from(context)
            .inflate(layoutId, parent, false)

        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Getting image from data list and setting to image
        val item = data?.get(position)

        holder.photoIv?.let {
            Glide.with(context)
                .load(item?.photoURI)
                .into(it)
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoIv: ImageView? by lazy { itemView.findViewById<ImageView>(R.id.photo_iv) }
    }
}
