package com.greg.netflixwanted.controllers

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greg.netflixwanted.databinding.ListItemSearchBinding
import com.greg.netflixwanted.interfaces.OnSearchClickListener

class SearchListCellHolder(listItemSearchBinding: ListItemSearchBinding, listener: OnSearchClickListener) : RecyclerView.ViewHolder(listItemSearchBinding.root) {

    private val binding = listItemSearchBinding
    private val listener = listener

    fun bindItems(search: String){
        Glide.with(binding.imgSearch.context).load(search).into(binding.imgSearch)
    }

}
