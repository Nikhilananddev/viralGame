package com.nikhilanand.viralgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nikhilanand.utils.Resource
import com.nikhilanand.viralgame.MainActivity
import com.nikhilanand.viralgame.R
import com.nikhilanand.viralgame.adapter.DogImageAdapter
import com.nikhilanand.viralgame.databinding.FragmentGenerateDogBinding
import com.nikhilanand.viralgame.model.DogImage


class GenerateDogFragment : Fragment() {

    lateinit var dogImageAdapter: DogImageAdapter
    lateinit var binding: FragmentGenerateDogBinding
    lateinit var viewModel: DogImageViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= FragmentGenerateDogBinding.inflate(inflater,container,false)

        return binding.root
//        return inflater.inflate(R.layout.fragment_generate_dog, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()
         binding.generateButton.setOnClickListener {
             viewModel.getDogImage()
         }


//        viewModel.dogImagesLiveData.observe(viewLifecycleOwner) { resource ->
//            when (resource) {
//                is Resource.Success -> {
//                    // Update the RecyclerView with the data
//                    dogImageAdapter.submitList(resource.data)
//                }
//                is Resource.Error -> {
//                    // Show an error message
//                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
//                }
//                is Resource.Loading -> {
//                    // Show a loading spinner
//                    progressBar.visibility = View.VISIBLE
//                }
//            }
//        }


//        viewModel.dogImage.observe(viewLifecycleOwner, Observer { response ->
//            when(response) {
//                is Resource.Success -> {
////                    hideProgressBar()
//
//
//                    response.data?.let { dogImageResponse ->
//
//
////                        val options = RequestOptions()
////                            .centerCrop()
////                            .placeholder(R.drawable.place_holder_image)
////                            .error(R.drawable.place_holder_image)
////
////                        Glide.with(binding.root.context)
////                            .load(dogImageResponse.message)
////                            .apply(options)
////                            .into(binding.generateDogImageView)//                        dogImageAdapter.differ.submitList(dogImageResponse.message)
////                        val totalPage=newsResponse.totalResults
////                        isLastPage=viewModel.breakingNewsPage==totalPage
////                        if(isLastPage)
////                        {
////                            rvBreakingNews.setPadding(0,0,0,0)
////                        }
//                    }
//                }
//                is Resource.Error -> {
////                    hideProgressBar()
//                    response.message?.let { message ->
//                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
//                    }
//                }
//                is Resource.Loading -> {
////                    showProgressBar()
//                }
//            }
//        })




//                viewModel.dogImages.observe(viewLifecycleOwner, Observer { response ->
//            when(response) {
//                is Resource.Success<*> -> {
////                    hideProgressBar()
//
//
//                    response.data?.let { dogImageResponse ->
//
//
////                        val options = RequestOptions()
////                            .centerCrop()
////                            .placeholder(R.drawable.place_holder_image)
////                            .error(R.drawable.place_holder_image)
////
////                        Glide.with(binding.root.context)
////                            .load(dogImageResponse.message)
////                            .apply(options)
////                            .into(binding.generateDogImageView)//
//                                                dogImageAdapter.differ.submitList(dogImageResponse.)
////                        val totalPage=newsResponse.totalResults
////                        isLastPage=viewModel.breakingNewsPage==totalPage
////                        if(isLastPage)
////                        {
////                            rvBreakingNews.setPadding(0,0,0,0)
////                        }
//                    }
//                }
//                is Resource.Error<*> -> {
////                    hideProgressBar()
//                    response.message?.let { message ->
//                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
//                    }
//                }
//                is Resource.Loading<*> -> {
////                    showProgressBar()
//                }
//            }
//        })

        viewModel.dogImagesLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    // Update the RecyclerView with the data
                    dogImageAdapter.differ.submitList(resource.data)
                }
                is Resource.Error -> {
                    // Show an error message
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    // Show a loading spinner
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
//                    progressBar.visibility = View.VISIBLE
                }
            }
        }



    }



    private fun setupRecyclerView() {
        dogImageAdapter = DogImageAdapter()
        binding.generateDogRecyclerView.apply {
            adapter = dogImageAdapter
            layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}