package com.nikhilanand.viralgame.repository

import android.content.Context
import com.nikhilanand.viralgame.api.RetrofitInstance
import com.nikhilanand.viralgame.model.DogImage
import com.nikhilanand.viralgame.util.CacheManager

class DogImageRepository {

    suspend fun getDogImage()=
        RetrofitInstance.api.getRandomDogImage()

    fun addDogImageToCache(key: String, dogImage: DogImage,context: Context) {
        CacheManager.put(key,dogImage,context)

    }
    fun clearCache(context: Context) {
        CacheManager.clear(context)

    }


}