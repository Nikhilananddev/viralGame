package com.nikhilanand.viralgame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhilanand.utils.Resource
import com.nikhilanand.viralgame.MainActivity
import com.nikhilanand.viralgame.R
import com.nikhilanand.viralgame.adapter.DogImageAdapter
import com.nikhilanand.viralgame.databinding.FragmentGenerateDogBinding
import com.nikhilanand.viralgame.databinding.FragmentGeneratedDogBinding
import com.nikhilanand.viralgame.model.DogImage
import com.nikhilanand.viralgame.util.CacheManager


class GeneratedDogFragment : Fragment() {

    lateinit var dogImageAdapter: DogImageAdapter
    lateinit var binding: FragmentGeneratedDogBinding
    lateinit var viewModel: DogImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentGeneratedDogBinding.inflate(inflater,container,false)

        return binding.root
//        return inflater.inflate(R.layout.fragment_generated_dog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()


        viewModel.getDogAllImage()


        viewModel.dogImages.observe(viewLifecycleOwner) { resource ->


//            if (resource!=null)
//            {
//                dogImageAdapter.differ.submitList(resource)
//            }
            dogImageAdapter.differ.submitList(resource)
        }
//
//        val list = CacheManager.getAllDogImage().toMutableList()



//        dogImageAdapter.differ.submitList(dogImageList)

        val a=2

//        viewModel.dogImagesLiveData.observe(viewLifecycleOwner) { resource ->
//            when (resource) {
//                is Resource.Success -> {
//                    // Update the RecyclerView with the data
//                    dogImageAdapter.differ.submitList(resource.data)
//                }
//                is Resource.Error -> {
//                    // Show an error message
//                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
//                }
//                is Resource.Loading -> {
//                    // Show a loading spinner
//                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
////                    progressBar.visibility = View.VISIBLE
//                }
//            }
//        }

    }


    private fun setupRecyclerView() {
        dogImageAdapter = DogImageAdapter()
        binding.generatedDogRecyclerView.apply {
            adapter = dogImageAdapter
            layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}