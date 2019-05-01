package com.company.archapp.models

import io.realm.RealmObject

open class Landmark : RealmObject() {
    var name: String = "" // Name | Имя
    var article: WikiArticle? = null // Article from wikipedia | Статья из Википедии
    var photo: Photo? =
        null // First photo from images which we receive from ethernet | Первое изображение которое мы получаем из интернета
    var position: Position? = null // Position (latitude; longitude) | Местоположение (широта; долгота)
}