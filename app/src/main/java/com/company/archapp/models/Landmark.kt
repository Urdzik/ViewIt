package com.company.archapp.models

import io.realm.RealmObject

open class Landmark : RealmObject() {
    var name: String = "" // Name
    var article: WikiArticle? = null // Article from wikipedia
    var photo: Photo? = null // First photo from images which we receive from ethernet
    var position: Position? = null // Position (latitude; longitude)
}