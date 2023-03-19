package com.nikhilanand.viralgame.model

import androidx.room.Entity

@Entity(tableName = "DogImage")
data class DogImage(val imageUrl: String)
