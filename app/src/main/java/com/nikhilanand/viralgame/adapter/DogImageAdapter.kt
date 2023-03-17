package com.nikhilanand.viralgame.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nikhilanand.viralgame.R
import com.nikhilanand.viralgame.databinding.ItemImagePreviewBinding
import com.nikhilanand.viralgame.model.DogImage

class DogImageAdapter() : RecyclerView.Adapter<DogImageAdapter.DogImageViewHolder>() {

    lateinit var binding: ItemImagePreviewBinding

        inner   class DogImageViewHolder(private val binding: ItemImagePreviewBinding):RecyclerView.ViewHolder(binding.root)

        {
            fun bind(itemData: DogImage){
                val options = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.place_holder_image)
                    .error(R.drawable.place_holder_image)

                Glide.with(binding.root.context)
                    .load(itemData.imageUrl)
                    .apply(options)
                    .into(binding.itemImage)

            }
        }

    private val differCallback=object:DiffUtil.ItemCallback<DogImage>(){
        override fun areItemsTheSame(oldItem: DogImage, newItem: DogImage): Boolean {

          return oldItem.imageUrl==newItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: DogImage, newItem: DogImage): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,differCallback)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageViewHolder {

       binding= ItemImagePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

      return  DogImageViewHolder(binding)
     }

     override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {

         val itemData = differ.currentList[position]
         holder.bind(itemData)
     }

     override fun getItemCount(): Int {
      return  differ.currentList.size

     }

 }