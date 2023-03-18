package com.nikhilanand.viralgame.util

import android.util.LruCache
import com.nikhilanand.viralgame.model.DogImage

object CacheManager {
    private val MAX_CACHE_SIZE = 20
    private val cache = LruCache<String, DogImage>(MAX_CACHE_SIZE)

    fun put(key: String, value: DogImage) {
        cache.put(key, value)
    }

    fun get(key: String): Any? {
        return cache.get(key)
    }

    fun remove(key: String) {
        cache.remove(key)
    }
    fun getAllDogImage():List<DogImage>
    {
        var list = ArrayList<DogImage>()
        val entries = cache.snapshot().entries
        for (entry in entries) {

            val value = entry.value
            list.add(value)
            // Do something with the key-value pair
        }
        return list
    }
}
