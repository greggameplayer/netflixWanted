package com.greg.netflixwanted

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.greg.netflixwanted.beans.ApiCallsResponse
import com.greg.netflixwanted.beans.SearchResponse
import com.greg.netflixwanted.beans.SearchResult
import com.greg.netflixwanted.controllers.MovieController
import com.greg.netflixwanted.controllers.RetrofitController
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Response
import kotlin.coroutines.CoroutineContext
import kotlin.properties.Delegates

class SearchActivity  : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job
    private var apiCalls by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_search)

        val compositeDisposable = CompositeDisposable()

        val btnMovie: Button = findViewById(R.id.btnMovie)

        btnMovie.setOnClickListener {

            compositeDisposable.add(
                RetrofitController.getRetrofitServiceMongo().getApiCalls()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({response -> onResponseGetApiCalls(response)}, {t -> onFailure(t) }))
        }
    }



    override fun onDestroy() {
        job.cancel() // cancel the Job
        super.onDestroy()
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
        println(t.message)
    }

    private fun onResponse(response: Any) {
        Toast.makeText(this,response.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun onResponseGetApiCalls(response: ApiCallsResponse) {
        val compositeDisposable = CompositeDisposable()

        apiCalls = response.document.calls

        if (apiCalls > 0) {
            compositeDisposable.add(RetrofitController.getRetrofitService().search(query = "test")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({t -> onResponseSearch(t) }, {t -> onFailure(t) }))
        } else {
            Toast.makeText(this@SearchActivity,"No more api calls available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onResponseSearch(response: Response<SearchResponse>) {
        val compositeDisposable = CompositeDisposable()


        compositeDisposable.add(
        RetrofitController.getRetrofitServiceMongo().updateApiCalls(body = RetrofitController.getRetrofitUpdateOneParam(response.headers().get("x-ratelimit-requests-remaining")))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({res -> onResponse(res)}, {t -> onFailure(t) }))

        val intent = Intent(this@SearchActivity, MovieController::class.java)
        intent.putExtra("srcImgMovie", response.body()?.results?.get(0)?.img)
        intent.putExtra("movieTitle", response.body()?.results?.get(0)?.title)
        intent.putExtra("countries",
            response.body()?.results?.get(0)?.clist?.indexOf(",\"more\"")
                ?.let { response.body()?.results?.get(0)?.clist?.substring(0, it) })
        startActivity(intent)
    }
}
