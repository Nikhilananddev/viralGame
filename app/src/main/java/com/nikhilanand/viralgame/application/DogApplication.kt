package com.nikhilanand.viralgame.application

import android.app.Application
import android.content.Context

class DogApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: DogApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        val context: Context = DogApplication.applicationContext()
    }
}