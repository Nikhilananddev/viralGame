package com.nikhilanand.viralgame.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikhilanand.utils.Resource
import com.nikhilanand.viralgame.application.DogApplication
import com.nikhilanand.viralgame.model.DogApiResponse
import com.nikhilanand.viralgame.model.DogImage
import com.nikhilanand.viralgame.repository.DogImageRepository
import com.nikhilanand.viralgame.util.CacheManager
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class DogImageViewModel(val app:Application,val dogImageRepository: DogImageRepository):
    AndroidViewModel(app){


    val dogImage: MutableLiveData<Resource<DogApiResponse>> = MutableLiveData()
    val dogImages:MutableLiveData<List<DogImage>> = MutableLiveData()



    val TAG =" DogImageViewModel"

    val dogImagesLiveData: MutableLiveData<Resource<List<DogImage>>> = MutableLiveData()




    var dogApiResponse:DogApiResponse? = null


    fun getDogImage() = viewModelScope.launch {
        safeDogImage()
    }




    fun clearAllDogImage() = viewModelScope.launch {

        try {
            dogImageRepository.clearCache(app.applicationContext)
            dogImages.postValue(CacheManager.getAllDogImage())
        }catch (t:Throwable)
        {
            when(t) {

                is IOException -> t.printStackTrace()
                else -> {

                    println("read write error")
                }
            }
        }

    }


    fun getDogAllImage() = viewModelScope.launch {
        saveAllDogImage()
    }

        suspend fun saveAllDogImage() {

      dogImages.postValue(CacheManager.getAllDogImage())
       }



    fun w() {
        val a= 2;
    }
    suspend fun safeDogImage(){
        dogImage.postValue(Resource.Loading())

        try {

            if(hasInternetConnection()) {
                val response = dogImageRepository.getDogImage()
                dogImage.postValue(handleDogImageResponse(response))
            } else{
                dogImage.postValue(Resource.Error("NO Internet Connection"))
            }





        }catch (t:Throwable)
        {
            when(t)
            {

                is IOException ->dogImage.postValue(Resource.Error("Network Failure"))
                else->{

                    dogImage.postValue(Resource.Error("Conversion Error"))}
            }
        }

    }

    private fun handleDogImageResponse(response: Response<DogApiResponse>): Resource<DogApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (dogApiResponse == null) {

                    dogApiResponse = resultResponse


                     addUrlToList(resultResponse)

                    dogImageRepository.addDogImageToCache(resultResponse.message, DogImage(resultResponse.message),app.applicationContext)


                } else {

                    dogApiResponse=resultResponse


                    addUrlToList(resultResponse)

                    dogImageRepository.addDogImageToCache(resultResponse.message, DogImage(resultResponse.message),app.applicationContext)

                }
                return Resource.Success(dogApiResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }



    private fun addUrlToList(resultResponse: DogApiResponse) {

        val dogImageList = dogImagesLiveData.value?.data?.toMutableList() ?: mutableListOf()

        if (dogImageList.size>=20)
        {
            dogImageList.removeAt(0)
        }


        dogImageList.add(DogImage(resultResponse.message))
        dogImagesLiveData.postValue(Resource.Success(dogImageList))

    }



    fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<DogApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

                else -> false
            }

        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true

                    else -> false

                }
            }
        }

        return false
    }

}