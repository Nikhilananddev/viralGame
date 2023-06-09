package com.nikhilanand.viralgame.util


class MaxSizeList<E>(private val maxSize: Int) : ArrayList<E>() {
    override fun add(e: E): Boolean {
        if (size == 2) {
            removeAt(0)
        }
        return super.add(e)
    }
}
