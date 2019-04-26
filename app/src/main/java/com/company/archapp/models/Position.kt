package com.company.archapp.models

import io.realm.RealmObject

open class Position : RealmObject() {
    var latitude: Double = 0.0
    var longitude: Double = 0.0
}
