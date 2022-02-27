package com.greg.netflixwanted.controllers

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.greg.netflixwanted.R

class MovieController : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie)

        val srcImgMovie : String = intent.extras?.get("srcImgMovie").toString()
        val movieTitle : String = intent.extras?.get("movieTitle").toString()
        val countries : String = intent.extras?.get("countries").toString()

        val btnMovie: ImageButton = findViewById(R.id.btn_back)
        val imgMovie: ImageView = findViewById(R.id.img_movie)
        val tvMovieTitle: TextView = findViewById(R.id.tv_movieTitle)
        val svCountries: ScrollView = findViewById(R.id.sv_countries)

        Glide.with(this).load(srcImgMovie).into(imgMovie)

        tvMovieTitle.text = movieTitle

        val countriesList = mutableListOf<String>()
        for (country in countries.split(",")){
            countriesList.add(country.split(":")[1].replace("\"", ""))
        }

        val wrapper = LinearLayout(this)
        wrapper.orientation = LinearLayout.VERTICAL
        for (country in countriesList) {
            val countryView = TextView(this)
            countryView.text = country
            countryView.setTextColor(Color.WHITE)
            wrapper.addView(countryView)
        }
        svCountries.addView(wrapper)

        btnMovie.setOnClickListener{
            this.finish()
        }
    }
}