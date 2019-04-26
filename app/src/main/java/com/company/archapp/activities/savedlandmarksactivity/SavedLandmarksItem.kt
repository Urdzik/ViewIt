package com.company.archapp.activities

import com.google.android.gms.maps.model.LatLng

class SavedLandmarksItem (
    var name: String,
    var article: String = "",
    var articleUrl: String = "",
    var photo: String = "",
    var position: LatLng? = null
)