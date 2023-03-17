package com.nikhilanand.viralgame.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikhilanand.utils.Resource
import com.nikhilanand.viralgame.model.DogApiResponse
import com.nikhilanand.viralgame.model.DogImage
import com.nikhilanand.viralgame.repository.DogImageRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class DogImageViewModel(val app:Application,val dogImageRepository: DogImageRepository):
    AndroidViewModel(app){
    val dogImage: MutableLiveData<Resource<DogApiResponse>> = MutableLiveData()
    val dogImages:MutableLiveData<List<DogImage>> = MutableLiveData()



    val currentDogImages = dogImages.value ?: emptyList()

    val dogImagesLiveData: MutableLiveData<Resource<List<DogImage>>> = MutableLiveData()


//    private val _dogImages = MutableLiveData<List<String>>()
//    val dogImages: LiveData<List<String>> = _dogImages
//
//    fun fetchRandomDogImage() {
//        viewModelScope.launch {
//            val response = repository.getRandomDogImage()
//            val currentDogImages = _dogImages.value ?: emptyList()
//            val newDogImages = currentDogImages.toMutableList()
//            newDogImages.add(response.message)
//            _dogImages.value = newDogImages.toList()
//        }
//    }

    var dogApiResponse:DogApiResponse? = null


    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        safeDogImage()
    }






    suspend fun safeDogImage(){
        dogImage.postValue(Resource.Loading())


        try {

//            if(hasInternetConnection()) {
//                val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
//                breakingNews.postValue(handleBreakingNewsResponse(response))
//            } else{
//                breakingNews.postValue(Resource.Error("NO Internet Connection"))
//            }


                val response = dogImageRepository.getDogImage()
                dogImage.postValue(handleDogImageResponse(response))

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
//                breakingNewsPage++
                if (dogApiResponse == null) {

                    dogApiResponse = resultResponse


//                    val newDogImages = currentDogImages.toMutableList()
//                    newDogImages.add(DogImage(resultResponse.message))
//                    dogImages.postValue(newDogImages.toList())

                } else {

                    dogApiResponse=resultResponse
//                    val oldImage = dogApiResponse?.message
//                    val newImage = resultResponse.message

//                    val newDogImages = currentDogImages.toMutableList()
//                    newDogImages.add(DogImage(resultResponse.message))
//                    dogImages.postValue(newDogImages.toList())
//                    oldImage?.all(newImage)
                }
                return Resource.Success(dogApiResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}