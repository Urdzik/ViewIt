package com.company.archapp.models

import io.realm.RealmObject

open class WikiArticle : RealmObject() {
    var article: String? = "" // article from Wikipedia | Статья из Википедии
    var uri: String? = "" // url of article | Ссылка на статью
}