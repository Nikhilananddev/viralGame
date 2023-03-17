package com.nikhilanand.viralgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nikhilanand.viralgame.R
import com.nikhilanand.viralgame.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

 lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.root
//        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.generateDog.setOnClickListener {
           findNavController().navigate(R.id.action_homeFragment_to_generateDogFragment)

        }
        binding.showRecentDog.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_generatedDogFragment)

        }


    }

}