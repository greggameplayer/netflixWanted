package com.greg.netflixwanted

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.greg.netflixwanted.controllers.RetrofitController
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class SearchActivity  : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_search)

        val compositeDisposable = CompositeDisposable()

        compositeDisposable.add(
            RetrofitController.getRetrofitServiceMongo().getApiCalls(body = RetrofitController.getRetrofitFindOneParam())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))

        compositeDisposable.add(
            RetrofitController.getRetrofitServiceMongo().updateApiCalls(body = RetrofitController.getRetrofitUpdateOneParam())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))
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
}
