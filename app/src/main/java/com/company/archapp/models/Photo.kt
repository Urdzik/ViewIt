package com.company.archapp.models

import io.realm.RealmObject

open class Photo : RealmObject() {
    var uri: String? = "" // Url of photo | Ссылка на картинку
}