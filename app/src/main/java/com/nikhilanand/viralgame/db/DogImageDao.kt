package com.nikhilanand.newsapp.db

import android.util.LruCache
import androidx.lifecycle.LiveData
import androidx.room.*
import com.nikhilanand.viralgame.model.DogImage

@Dao
interface DogImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogImage: DogImage)

    @Query("SELECT * from DogImage")
    fun getAllCache():LiveData<DogImage>

    @Delete
    suspend fun deleteArticle(dogImage: DogImage)


}