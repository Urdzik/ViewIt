package com.company.archapp

import android.app.Application
import io.realm.Realm

/**
 * Application class
 * Класс приложения
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Init Realm
        // Иницилизируем реалм
        Realm.init(this)
    }
}