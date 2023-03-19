package com.nikhilanand.viralgame.model

import android.util.LruCache

data class DogImageCache( private val cache:LruCache<String, DogImage>)