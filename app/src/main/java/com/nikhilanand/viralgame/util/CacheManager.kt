package com.nikhilanand.viralgame.util

import android.app.Activity
import android.content.Context
import android.util.Log
import android.util.LruCache
import com.nikhilanand.viralgame.model.DogImage
import kotlinx.coroutines.GlobalScope.coroutineContext
import java.io.*

object CacheManager {
    private val MAX_CACHE_SIZE = 20
//    private val cache = LruCache<String, DogImage>(MAX_CACHE_SIZE)
    private val cache = readCache()
    val a=2


    fun put(key: String, value: DogImage,context: Context) {
        cache.put(key, value)
        saveCache(context)

    }

    fun get(key: String): Any? {
        return cache.get(key)
    }

    fun remove(key: String,context: Context) {
        cache.remove(key)
        saveCache(context)

    }

    fun clear(context: Context){
        cache.evictAll()
        saveCache(context)

    }
    fun getAllDogImage():List<DogImage>
    {

        var list = ArrayList<DogImage>()
        val entries = cache.snapshot().entries
        for (entry in entries) {
            val value = entry.value as? DogImage
            if (value != null) {
                list.add(value)
            }
            // Do something with the key-value pair
        }


        return list
    }

//    private fun saveCache() {
////        val file = File("cache.dat")
////        val outputStream = ObjectOutputStream(FileOutputStream(file))
////        outputStream.writeObject(cache)
////        outputStream.close()
//
//    }
fun saveCache(context: Context) {
    try {
        val file = File(context.cacheDir, "CACHE_FILE_NAME")
        val fileOutputStream = FileOutputStream(file)
        val objectOutputStream = ObjectOutputStream(fileOutputStream)
        objectOutputStream.writeObject(cache.snapshot())
        objectOutputStream.close()
        fileOutputStream.close()
    } catch (e: Exception) {
//        Log.e(TAG, "Error saving cache", e)
    }
}


    private fun readCache(): LruCache<String, DogImage> {
        val file = File("cache.dat")
        if (file.exists()) {
            val inputStream = ObjectInputStream(FileInputStream(file))
            val cache = inputStream.readObject() as LruCache<String, DogImage>
            inputStream.close()
            return cache
        }
        return LruCache<String, DogImage>(MAX_CACHE_SIZE)
    }
}
