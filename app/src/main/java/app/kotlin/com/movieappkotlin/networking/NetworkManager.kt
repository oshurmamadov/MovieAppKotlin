package app.kotlin.com.movieappkotlin.networking

import android.util.Log
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
    fun getPopularMovies(): Observable<PopularMovie>{
        return Observable.create { subscriber ->

            val restAPI = RestAPI()
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
