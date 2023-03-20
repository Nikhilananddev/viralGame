package com.nikhilanand.viralgame.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.nikhilanand.newsapp.db.DogImageDatabase
import com.nikhilanand.viralgame.api.RetrofitInstance
import com.nikhilanand.viralgame.model.DogImage
import com.nikhilanand.viralgame.util.CacheManager

class DogImageRepository(val dogImageDatabase: DogImageDatabase) {

    suspend fun getDogImage()=
        RetrofitInstance.api.getRandomDogImage()

    fun addDogImageToCache(key: String, dogImage: DogImage,context: Context) {
        CacheManager.put(key,dogImage,context)

    }
    fun clearCache(context: Context) {
        CacheManager.clear(context)

    }

    suspend fun insertDogImage(dogImage: DogImage) =dogImageDatabase.getDogImageDao().insert(dogImage)

    fun getAllDogImage()=dogImageDatabase.getDogImageDao().getAllCache()

    suspend fun deleteDogImage() =dogImageDatabase.getDogImageDao().deleteDogImage()


}