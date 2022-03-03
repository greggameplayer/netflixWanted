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

        //Récupération des informations de l'api concernant le film ou la serie depuis la vue de recherche
        val srcImgMovie: String = intent.extras?.get("srcImgMovie").toString()
        val movieTitle: String = intent.extras?.get("movieTitle").toString()
        val countries: String = intent.extras?.get("countries").toString()

        //Instanciation des éléments utilisés dans le code
        val btnMovie: ImageButton = findViewById(R.id.btn_back)
        val imgMovie: ImageView = findViewById(R.id.img_movie)
        val tvMovieTitle: TextView = findViewById(R.id.tv_movieTitle)
        val svCountries: ScrollView = findViewById(R.id.sv_countries)

        //Définition de la cover du film ou de la serie depuis son url dans la vue
        Glide.with(this).load(srcImgMovie).into(imgMovie)

        //Définition du titre du film dans la vue
        tvMovieTitle.text = movieTitle

        //Transformation des données des pays dispo brute provenant de l'api depuis de la vue de recherche pour les rendre exploitable
        val countriesList = mutableListOf<String>()
        for (country in countries.split(",")) {
            countriesList.add(country.split(":")[1].replace("\"", ""))
        }

        //Création du future children du scroll view (unique)
        val wrapper = LinearLayout(this)
        wrapper.orientation = LinearLayout.VERTICAL
        for (country in countriesList) {

            //Création, pour chaque pays, d'une ligne de données
            val countryWrapper = LinearLayout(this)
            countryWrapper.orientation = LinearLayout.HORIZONTAL

            //Ajout du drapeau du pays grace à la base de données sqllite
            val contryFlag = ImageView(this)
            //Exécution asynchrone de la récupération
            launch {
                //requête de récupération de l'url de l'image
                val imgUrl =  (application as Application).repository.getCountry(country.trim()).img

                //Définition du drapeau du pays depuis son url provenant de la bdd
                Glide.with(this@MovieController)
                    .load(imgUrl)
                    .override(175, 175)
                    .into(contryFlag)
            }
            countryWrapper.addView(contryFlag)

            //Ajout du nom du pays
            val countryName = TextView(this)
            countryName.text = "     " + country
            //Définition de la couleur de text
            countryName.setTextColor(Color.WHITE)
            //Définition de la taille du text
            countryName.textSize = 18F
            countryWrapper.addView(countryName)
            wrapper.addView(countryWrapper)

            //Ajout d'un spacer pour écarter les lignes pour améliorer la visibilité (pas de définition de margin possible :/)
            val spacer = TextView(this)
            spacer.height = 20
            wrapper.addView(spacer)
        }
        ///Ajout du children du scroll view (unique)
        svCountries.addView(wrapper)

        //Permet de revenir en arrière
        btnMovie.setOnClickListener {
            this.finish()
        }
    }

    override fun onDestroy() {
        job.cancel() // cancel the Job
        super.onDestroy()
    }
}
