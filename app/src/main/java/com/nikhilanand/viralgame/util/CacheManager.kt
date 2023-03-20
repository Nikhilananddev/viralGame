package com.nikhilanand.viralgame.util

import android.util.LruCache
import com.nikhilanand.viralgame.application.DogApplication
import com.nikhilanand.viralgame.model.DogImage
import java.io.*

object CacheManager {
    private const val MAX_CACHE_SIZE = 20
    private val cache = readCache()


    fun put(key: String, value: DogImage) {
        cache.put(key, value)
        saveCache()

    }

    fun get(key: String): Any? {
        return cache.get(key)
    }

    fun remove(key: String) {
        cache.remove(key)
        saveCache()

    }

    fun clear() {
        cache.evictAll()
        saveCache()

    }

    fun getAllDogImage(): List<DogImage> {

        var list = ArrayList<DogImage>()
        val entries = cache.snapshot().entries
        for (entry in entries) {
            val value = entry.value as? DogImage
            if (value != null) {
                list.add(value)
            }
        }


        return list
    }


    fun saveCache() {
        try {
            val file = File(DogApplication.applicationContext().cacheDir, "CACHE_FILE_NAME")
            val fileOutputStream = FileOutputStream(file)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(cache.snapshot())
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: Exception) {
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
