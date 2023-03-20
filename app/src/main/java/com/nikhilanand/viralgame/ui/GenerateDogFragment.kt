package com.nikhilanand.viralgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhilanand.viralgame.util.Resource
import com.nikhilanand.viralgame.MainActivity
import com.nikhilanand.viralgame.adapter.DogImageAdapter
import com.nikhilanand.viralgame.databinding.FragmentGenerateDogBinding


class GenerateDogFragment : Fragment() {

    lateinit var dogImageAdapter: DogImageAdapter
    lateinit var binding: FragmentGenerateDogBinding
    lateinit var viewModel: DogImageViewModel
    var isLoading = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGenerateDogBinding.inflate(inflater, container, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()
        binding.generateButton.setOnClickListener {
            viewModel.getDogImage()
        }




        viewModel.dogImagesLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideProgressBar()
                    // Update the RecyclerView with the data
                    dogImageAdapter.differ.submitList(resource.data)
                }
                is Resource.Error -> {
                    hideProgressBar()
                    // Show an error message
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    // Show a loading spinner
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
//                    progressBar.visibility = View.VISIBLE
                    showProgressBar()
                }
            }
        }


    }


    private fun setupRecyclerView() {
        dogImageAdapter = DogImageAdapter()
        binding.generateDogRecyclerView.apply {
            adapter = dogImageAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }
}