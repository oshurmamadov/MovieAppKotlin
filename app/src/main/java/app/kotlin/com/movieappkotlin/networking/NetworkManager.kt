package app.kotlin.com.movieappkotlin.networking

import android.util.Log
import app.kotlin.com.movieappkotlin.models.Configuration
import app.kotlin.com.movieappkotlin.models.Movie
import app.kotlin.com.movieappkotlin.models.PopularMovie
import app.kotlin.com.movieappkotlin.utils.API_KEY
import app.kotlin.com.movieappkotlin.utils.ERROR_TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Observable

/**
 * Created by parviz on 7/10/16.
 */
class NetworkManager(){
    private val restAPI = RestAPI()

    fun getConfigurations():Observable<Configuration>{
        return Observable.create { subscriber->
            val call = restAPI.getMovieApiService().getConfiguration(API_KEY)
            call.enqueue(object: Callback<Configuration>{
                override fun onResponse(call: Call<Configuration>?, response: Response<Configuration>) {
                    subscriber.onNext(response.body())
                    subscriber.onCompleted()
                }

                override fun onFailure(call: Call<Configuration>?, t: Throwable?) {
                    Log.e(ERROR_TAG,t.toString())
                }

            })
        }
    }

    fun getPopularMovies(): Observable<PopularMovie>{
        return Observable.create { subscriber ->

            val call = restAPI.getMovieApiService().getPopularMovies(API_KEY)

            /*
            * Synchronous call
            */
//            val result = callResponse.execute()
//            if(result.isSuccessful){
//                subscriber.onNext(result.body())
//                subscriber.onCompleted()
//            }else{
//                Log.e(ERROR_TAG,result.message())
//            }

           /*
           * Asynchronous call
           */
            call.enqueue(object: Callback<PopularMovie>{
                override fun onResponse(call: Call<PopularMovie>?, response: Response<PopularMovie>) {
                    subscriber.onNext(response.body())
                    subscriber.onCompleted()
                }

                override fun onFailure(call: Call<PopularMovie>?, t: Throwable?) {
                    Log.e(ERROR_TAG,t.toString())
                }

            })


        }

    }
}
