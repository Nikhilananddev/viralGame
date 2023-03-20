
package com.nikhilanand.viralgame.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.nikhilanand.viralgame.model.DogImage

@Dao
interface DogImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogImage: DogImage)

    @Query("SELECT * FROM dogImages")
    fun getAllCache():MutableLiveData<List<DogImage>>

     @Query("DELETE FROM dogImages")
    suspend fun deleteDogImage()


}