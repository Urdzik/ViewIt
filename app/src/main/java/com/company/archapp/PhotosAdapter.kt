package com.company.archapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide


// Simple recyclerview adapter
class PhotosAdapter(val data: List<PhotoItem>?, val context: Context) :
    RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.photo_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Getting image from data list and setting to image
        val photoItem = data?.get(position)

        holder.photoIv?.let {
            Glide.with(context)
                .load(photoItem?.photoURI)
                .into(it)
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoIv: ImageView? by lazy { itemView.findViewById<ImageView>(R.id.photo_iv) }
    }
}