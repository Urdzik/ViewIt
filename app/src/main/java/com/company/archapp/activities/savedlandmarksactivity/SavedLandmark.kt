package com.company.archapp.activities.savedlandmarksactivity

import com.google.android.gms.maps.model.LatLng

class SavedLandmark(
    var name: String,
    var article: String = "",
    var articleUrl: String = "",
    var photo: String = "",
    var position: LatLng? = null
)