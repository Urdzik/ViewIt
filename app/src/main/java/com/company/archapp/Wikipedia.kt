package com.company.archapp

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView


@Suppress("DEPRECATION")
class Wikipedia {

    private lateinit var url: String

    fun findWikipediaUrl(word: String, textview: TextView) {
        //Меняем пробелы на "_"
        word.replace(" ".toRegex(), "_")

        //Добавляем название достопримечательности к ссылке
        url = "https://en.wikipedia.org/wiki/$word"

        //Добавляем гиперссылку
        val value = "<html><a href=\"$url\">$url</a></html>"
        textview.text = Html.fromHtml(value)
        textview.movementMethod = LinkMovementMethod.getInstance()
    }

}