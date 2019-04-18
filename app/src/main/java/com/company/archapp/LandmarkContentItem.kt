package com.company.archapp

import com.google.android.gms.maps.model.LatLng


class LandmarkContentItem {

    val viewType: Int
    lateinit var photoURI: String
    lateinit var latLng: LatLng

    constructor(value: String) {
        this.viewType = VIEW_IMAGE
        this.photoURI = value
    }

    constructor(value: LatLng) {
        this.viewType = VIEW_MAP
        this.latLng = value
    }

    companion object {
        val VIEW_IMAGE = 1
        val VIEW_MAP = 2
    }
}
