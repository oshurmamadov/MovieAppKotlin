package app.kotlin.com.movieappkotlin.networking

import android.util.Log
import app.kotlin.com.movieappkotlin.models.Movie
import app.kotlin.com.movieappkotlin.models.PopularMovie
import app.kotlin.com.movieappkotlin.utils.API_KEY
import rx.Observable

/**
 * Created by parviz on 7/10/16.
 */
class NetworkManager(){
    fun getPopularMovies(): Observable<PopularMovie>{
        return Observable.create { subscriber ->

            val restAPI = RestAPI()
            val callResponse = restAPI.getMovieApiService().getPopularMovies(API_KEY)

            val result = callResponse.execute()

            if(result.isSuccessful){
//                val movies = result.body().results.map {
//                    Movie(it.vote_average,it.title)
//                }

                subscriber.onNext(result.body())
                subscriber.onCompleted()


               // Log.e("Drakon",result.body().results.toString())
            }else{
                Log.e("Drakon",result.message())
            }
        }

    }
}
