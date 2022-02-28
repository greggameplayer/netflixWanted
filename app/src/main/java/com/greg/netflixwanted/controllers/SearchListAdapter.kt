package com.greg.netflixwanted.controllers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greg.netflixwanted.beans.SearchResult
import com.greg.netflixwanted.databinding.ListItemSearchBinding
import com.greg.netflixwanted.interfaces.OnSearchClickListener

class SearchListAdapter(searchArray: List<SearchResult>, listener: OnSearchClickListener) : RecyclerView.Adapter<SearchListCellHolder>() {

    private val dataSource : List<SearchResult> = searchArray
    private val listener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListCellHolder {
        val searchListCellBinding = ListItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchListCellHolder(searchListCellBinding, listener)
    }

    override fun onBindViewHolder(holder: SearchListCellHolder, position: Int) {
        holder.bindItems(dataSource[position].img)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

}
