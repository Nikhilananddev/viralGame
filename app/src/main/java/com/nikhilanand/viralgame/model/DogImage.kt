package com.nikhilanand.viralgame.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "dogImages")
data class DogImage(
    @PrimaryKey(autoGenerate = true)
    var id: Int?= null,      //adding id as primary key to the table and the class
    val imageUrl: String
    ): Serializable
