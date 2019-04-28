package com.company.archapp.activities.resultactivity

import com.google.android.gms.maps.model.LatLng

/**
 *  Class with data for Discrete scroll view
 *  Класс с данными для Discrete scroll view
 */
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
        const val VIEW_IMAGE = 1
        const val VIEW_MAP = 2
    }
}
