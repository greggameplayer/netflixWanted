package com.greg.netflixwanted.controllers

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.greg.netflixwanted.Search
import com.greg.netflixwanted.beans.SearchResult
import com.greg.netflixwanted.databinding.ListItemSearchBinding
import com.greg.netflixwanted.interfaces.OnSearchClickListener

class SearchListCellHolder(listItemSearchBinding: ListItemSearchBinding, listener: OnSearchClickListener) : RecyclerView.ViewHolder(listItemSearchBinding.root) {

    private val binding = listItemSearchBinding
    private val listener = listener

    fun bindItem(search: SearchResult){

    }

}