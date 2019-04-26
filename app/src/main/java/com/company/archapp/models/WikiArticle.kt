package com.company.archapp.models

import io.realm.RealmObject

open class WikiArticle : RealmObject() {
    var article: String? = "" // article from Wikipedia
    var uri: String? = "" // url of article
}