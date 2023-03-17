package com.nikhilanand.viralgame.repository

import com.nikhilanand.viralgame.api.RetrofitInstance

class DogImageRepository {

    suspend fun getDogImage()=
        RetrofitInstance.api.getRandomDogImage()
}