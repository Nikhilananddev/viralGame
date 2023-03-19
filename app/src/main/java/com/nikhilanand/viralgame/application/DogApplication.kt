
package com.nikhilanand.viralgame.application
import android.app.Application
import android.content.Context

class DogApplication:Application() {

    fun getAppContext(): Context? {
        return applicationContext
    }
}