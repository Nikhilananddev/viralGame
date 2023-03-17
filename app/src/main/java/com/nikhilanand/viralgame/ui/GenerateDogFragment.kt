package com.nikhilanand.viralgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhilanand.viralgame.R
import com.nikhilanand.viralgame.adapter.DogImageAdapter
import com.nikhilanand.viralgame.databinding.FragmentGenerateDogBinding


class GenerateDogFragment : Fragment() {

    lateinit var dogImageAdapter: DogImageAdapter
    lateinit var binding: FragmentGenerateDogBinding

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

//        setupRecyclerView()
    }


//
//    private fun setupRecyclerView() {
//        dogImageAdapter = DogImageAdapter()
//        binding.generateDogRecyclerView.apply {
//            adapter = dogImageAdapter
//            layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//        }
//    }
}