package com.nikhilanand.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nikhilanand.viralgame.model.DogImageCache


@Database(
    entities = [DogImageCache::class],
    version = 1
)
abstract class DogImageDatabase : RoomDatabase() {

    abstract fun getDogImageDao():DogImageDao

    companion object {
        @Volatile
        private var instance: DogImageDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK)
        {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =

            Room.databaseBuilder(
                context.applicationContext,
                DogImageDatabase::class.java,
                "dogImage_db.db"

            ).build()


    }


}