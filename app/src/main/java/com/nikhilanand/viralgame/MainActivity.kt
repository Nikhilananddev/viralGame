package com.nikhilanand.viralgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.nikhilanand.viralgame.databinding.ActivityMainBinding
import com.nikhilanand.viralgame.repository.DogImageRepository
import com.nikhilanand.viralgame.ui.DogImageViewModel
import com.nikhilanand.viralgame.ui.DogViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel:DogImageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding=  ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root


        setContentView(view)
        val dogImageRepository = DogImageRepository()
        val viewModelProviderFactory = DogViewModelProviderFactory(application, dogImageRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(DogImageViewModel::class.java)
        val navHostFragment= supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController= navHostFragment.navController


    }
}