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
    var isLoading=false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentGeneratedDogBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()


        viewModel.getDogAllImage()




        binding.clear.setOnClickListener {
            viewModel.clearAllDogImage()
        }
        viewModel.dogImages.observe(viewLifecycleOwner) { resource ->
            showProgressBar()


            if (resource!=null)
            {
                hideProgressBar()

                dogImageAdapter.differ.submitList(resource)
            }
        }






    }


    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading=false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading=true
    }
    private fun setupRecyclerView() {
        dogImageAdapter = DogImageAdapter()
        binding.generatedDogRecyclerView.apply {
            adapter = dogImageAdapter
            layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}