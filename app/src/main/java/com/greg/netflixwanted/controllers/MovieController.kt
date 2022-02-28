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
import com.greg.netflixwanted.Application
import com.greg.netflixwanted.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieController : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
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

        launch {
            val result = (application as Application).repository.getAll()
            println(result)

            println((application as Application).repository.getCountry("United States"))
        }
    }

    override fun onDestroy() {
        job.cancel() // cancel the Job
        super.onDestroy()
    }
}
