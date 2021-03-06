package com.greg.netflixwanted

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.greg.netflixwanted.beans.ApiCallsResponse
import com.greg.netflixwanted.beans.SearchResponse
import com.greg.netflixwanted.beans.SearchResult
import com.greg.netflixwanted.controllers.RetrofitController
import com.greg.netflixwanted.controllers.SearchListAdapter
import com.greg.netflixwanted.databinding.ActivitySearchBinding
import com.greg.netflixwanted.interfaces.OnSearchClickListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.coroutines.CoroutineContext
import kotlin.properties.Delegates

class SearchActivity : AppCompatActivity(), CoroutineScope, OnSearchClickListener, AdapterView.OnItemSelectedListener {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    lateinit var binding: ActivitySearchBinding

    private lateinit var job: Job
    private var apiCalls by Delegates.notNull<Int>()
    private var selectedItem: String = "nom"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        job = Job()
        fillSpinner()
        setContentView(binding.root)

        binding.btSearch.setOnClickListener { search() }
    }

    private fun search() {
        val compositeDisposable = CompositeDisposable()

        compositeDisposable.add(RetrofitController.getRetrofitServiceMongo().getApiCalls()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ t -> onResponseGetApiCalls(t) }, { t -> onFailure(t) })
        )
    }

    private fun afficheListSearch(listSearch: List<SearchResult>) {
        binding.searchRecyclerView.setHasFixedSize(true)
        val searchListLayoutManager = GridLayoutManager(this, 3)
        binding.searchRecyclerView.layoutManager = searchListLayoutManager
        val searchListAdapter = SearchListAdapter(listSearch, this)
        binding.searchRecyclerView.adapter = searchListAdapter
    }

    override fun onSearchClick(search: SearchResult) {
        TODO("Not yet implemented")
    }


    override fun onDestroy() {
        job.cancel() // cancel the Job
        super.onDestroy()
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
        println(t.message)
    }

    private fun onResponse(response: Any) {
        //
    }

    private fun onResponseGetApiCalls(response: ApiCallsResponse) {
        runBlocking {
            val compositeDisposable = CompositeDisposable()
//        val intent = Intent(this@SearchActivity, MovieController::class.java)
//        intent.putExtra("pays", response.body()?.results?.get(0)?.country)

            apiCalls = response.document.calls

            val bySpinnerValue = binding.spinner.selectedItem.toString() === "Nom"

            var countriesId = ""

            val countriesList: List<String>

            if (!bySpinnerValue) {
                countriesList = binding.editTextSearch.text.toString().split(",")
                countriesList.forEach {
                    val currentJob = launch {
                        countriesId =
                            (application as Application).repository.getCountriesByName(it)[0].id.toString() + ","
                    }

                    currentJob.join()
                }
                countriesId = countriesId.dropLast(1)
            }

            println("countriesId : $countriesId")

            if (apiCalls > 0) {
                compositeDisposable.add(RetrofitController.getRetrofitService().search(
                    query = if (bySpinnerValue) binding.editTextSearch.text.toString() else "",
                    countries = if (!bySpinnerValue) countriesId else null
                )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ t -> onResponseSearch(t) }, { t -> onFailure(t) })
                )
            } else {
                Toast.makeText(this@SearchActivity, "No more api calls available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onResponseSearch(response: Response<SearchResponse>) {
        val compositeDisposable = CompositeDisposable()

        compositeDisposable.add(
            RetrofitController.getRetrofitServiceMongo().updateApiCalls(
                body = RetrofitController.getRetrofitUpdateOneParam(
                    response.headers().get("x-ratelimit-requests-remaining")
                )
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ res -> onResponse(res) }, { t -> onFailure(t) })
        )

        response.body()?.let { afficheListSearch(it.results) }
        //val intent = Intent(this@SearchActivity, MovieController::class.java)
        //intent.putExtra("srcImgMovie", response.body()?.results?.get(0)?.img)
        //intent.putExtra("movieTitle", response.body()?.results?.get(0)?.title)
        //intent.putExtra("countries",
        //    response.body()?.results?.get(0)?.clist?.indexOf(",\"more\"")
        //        ?.let { response.body()?.results?.get(0)?.clist?.substring(0, it) })
        //startActivity(intent)
    }

    private fun fillSpinner() {
        val arraySearchType = arrayListOf("Nom", "Pays")
        val spinner: Spinner = binding.spinner
        val adapterSearchType: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.spinner_list, arraySearchType)
        spinner.adapter = adapterSearchType
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p2) {
            0 -> selectedItem = "nom"
            1 -> selectedItem = "pays"
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        selectedItem = "nom"
    }
}

