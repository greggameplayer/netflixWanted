package com.greg.netflixwanted.beans

import java.util.*

data class SearchResult(val avgrating: Float, val clist: String, val id : Int, val imdbrating: Float, val img : String,
                        val poster : String, val synopsis : String, val title : String, val titledate: Date, val year : Int)
