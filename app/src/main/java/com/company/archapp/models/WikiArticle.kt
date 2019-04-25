package com.company.archapp.models

import io.realm.RealmObject

open class WikiArticle : RealmObject() {
    var article: String? = ""
    var uri: String? = ""
}