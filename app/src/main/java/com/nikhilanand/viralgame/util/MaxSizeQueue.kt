package com.nikhilanand.viralgame.util

import java.util.*

class MaxSizeQueue<T>(private val maxSize: Int) : ArrayDeque<T>() {
    override fun add(t: T): Boolean {
        if (size >= maxSize) {
            remove()
        }
        return super.add(t)
    }
}