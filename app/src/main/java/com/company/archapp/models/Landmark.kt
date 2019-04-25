package com.company.archapp.models

import io.realm.RealmObject

open class Landmark : RealmObject() {
    var name: String = "" // Name
    var article: WikiArticle? = null // Article from wikipedia
    var photo: Photo? = null // First photo from images which we receive from ethernet
    var position: List<Double>? = null // Two items: 1. Latitude; 2. Longitude
}