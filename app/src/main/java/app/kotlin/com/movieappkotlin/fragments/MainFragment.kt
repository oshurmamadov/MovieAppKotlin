package app.kotlin.com.movieappkotlin.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import app.kotlin.com.movieappkotlin.R
import app.kotlin.com.movieappkotlin.adapters.MoviesAdapter
import app.kotlin.com.movieappkotlin.models.Configuration
import app.kotlin.com.movieappkotlin.models.Images
import app.kotlin.com.movieappkotlin.models.Movie
import app.kotlin.com.movieappkotlin.networking.NetworkManager
import app.kotlin.com.movieappkotlin.networking.RestAPI
import app.kotlin.com.movieappkotlin.utils.API_KEY
import app.kotlin.com.movieappkotlin.utils.CONFIG
import app.kotlin.com.movieappkotlin.utils.ERROR_TAG
import kotlinx.android.synthetic.main.fragment_main.*
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class MainFragment : Fragment() {
    private val movieList by lazy{ mList }
    private val networkManager by lazy{ NetworkManager()}
    private var subscription = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val images = (activity.intent.getSerializableExtra(CONFIG) as Configuration).images

        movieList.setHasFixedSize(true)
        movieList.layoutManager = LinearLayoutManager(context)

        val subscriber = networkManager.getPopularMovies()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                {
                    receivedData ->
                    val movies = receivedData.results
                    movieList.adapter = MoviesAdapter(movies,images)
                },
                {
                    onError ->
                    Log.e(ERROR_TAG,onError.message)
                }
        )
        subscription.add(subscriber)

    }

    override fun onPause() {
        super.onPause()
        subscription.clear()
    }

    override fun onResume() {
        super.onResume()
        subscription = CompositeSubscription()
    }

}
