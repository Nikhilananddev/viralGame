package com.nikhilanand.viralgame.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikhilanand.viralgame.repository.DogImageRepository


class DogViewModelProviderFactory(
    val app: Application,
    val dogImageRepository: DogImageRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DogImageViewModel(app, dogImageRepository) as T
    }


}