package com.nikhilanand.viralgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhilanand.utils.Resource
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


        setupRecyclerView()

        viewModel.dogImage.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
//                    hideProgressBar()


                    response.data?.let { dogImageResponse ->



//                        dogImageAdapter.differ.submitList(dogImageResponse.message)
//                        val totalPage=newsResponse.totalResults
//                        isLastPage=viewModel.breakingNewsPage==totalPage
//                        if(isLastPage)
//                        {
//                            rvBreakingNews.setPadding(0,0,0,0)
//                        }
                    }
                }
                is Resource.Error -> {
//                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
//                    showProgressBar()
                }
            }
        })


    }



    private fun setupRecyclerView() {
        dogImageAdapter = DogImageAdapter()
        binding.generateDogRecyclerView.apply {
            adapter = dogImageAdapter
            layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}