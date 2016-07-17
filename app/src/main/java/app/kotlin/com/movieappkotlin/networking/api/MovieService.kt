package app.kotlin.com.movieappkotlin.networking.api

import app.kotlin.com.movieappkotlin.models.Configuration
import app.kotlin.com.movieappkotlin.models.PopularMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by parviz on 7/10/16.
 */

interface MovieService{
    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") key: String): Call<PopularMovie>

    @GET("/3/configuration")
    fun getConfiguration(@Query("api_key") key: String): Call<Configuration>
}