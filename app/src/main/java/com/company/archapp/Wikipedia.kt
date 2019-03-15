package com.company.archapp


class Wikipedia{

    var url: String = "https://en.wikipedia.org/wiki/"

    fun findUrl(word: String): String {
        //Меняем пробелы на "_"
        word.replace(" ".toRegex(), "_")
        //Добавляем название достопримечательности к ссылке
        url += word
        return url
    }

}