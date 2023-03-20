package com.nikhilanand.viralgame.repository

import android.content.Context
import com.nikhilanand.viralgame.api.RetrofitInstance
import com.nikhilanand.viralgame.model.DogImage
import com.nikhilanand.viralgame.util.CacheManager

class DogImageRepository {

    suspend fun getDogImage() =
        RetrofitInstance.api.getRandomDogImage()

    fun addDogImageToCache(key: String, dogImage: DogImage) {
        CacheManager.put(key, dogImage)

    }

    fun clearCache() {
        CacheManager.clear()

    }


}