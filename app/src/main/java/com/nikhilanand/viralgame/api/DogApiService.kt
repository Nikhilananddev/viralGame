package com.nikhilanand.viralgame.api

import com.nikhilanand.viralgame.model.DogApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<DogApiResponse>
}
