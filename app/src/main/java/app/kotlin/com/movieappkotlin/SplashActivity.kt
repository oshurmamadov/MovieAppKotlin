package app.kotlin.com.movieappkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import app.kotlin.com.movieappkotlin.adapters.MoviesAdapter
import app.kotlin.com.movieappkotlin.networking.NetworkManager
import app.kotlin.com.movieappkotlin.utils.CONFIG
import app.kotlin.com.movieappkotlin.utils.ERROR_TAG
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class SplashActivity : AppCompatActivity() {
    private val networkManager by lazy{ NetworkManager() }
    private var subscription = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /* TODO - implement kotlin style runnable */
        Handler().postDelayed(Runnable { loadConfigurations() }, 800)
       // loadConfigurations()

    }

    fun loadConfigurations(){
        val subscriber = networkManager.getConfigurations()
        .subscribeOn(Schedulers.io() )
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                {
                    receivedData ->

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(CONFIG, receivedData)
                    startActivity(intent)
                    finish()
                },
                {
                    onError ->
                    Log.e(ERROR_TAG,onError.message)
                })
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
