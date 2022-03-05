package com.greg.netflixwanted.controllers

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greg.netflixwanted.beans.SearchResult
import com.greg.netflixwanted.databinding.ListItemSearchBinding
import com.greg.netflixwanted.interfaces.OnSearchClickListener

class SearchListCellHolder(listItemSearchBinding: ListItemSearchBinding, listener: OnSearchClickListener
) : RecyclerView.ViewHolder(listItemSearchBinding.root) {

    private val binding = listItemSearchBinding
    private val listener = listener

    private var searchResult: SearchResult? = null

    fun bindItems(search: String){
        Glide.with(binding.imgSearch.context).load(search).into(binding.imgSearch)
    }

    fun bindIndex(index: SearchResult){
        searchResult = index
    }

    init {
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context, MovieController::class.java)
            intent.putExtra("srcImgMovie", searchResult?.img)
            intent.putExtra("movieTitle", searchResult?.title)
            intent.putExtra("countries", searchResult?.clist?.indexOf(",\"more\"")
                ?.let { searchResult?.clist?.substring(0, it) })
            binding.root.context.startActivity(intent)
        }
    }

}
