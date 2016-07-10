package app.kotlin.com.movieappkotlin.networking

import app.kotlin.com.movieappkotlin.networking.api.MovieService
import app.kotlin.com.movieappkotlin.utils.ROOT
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by parviz on 7/10/16.
 */

class RestAPI{

//    fun getRetrofit(): Retrofit{
//        return Retrofit.Builder()
//        .baseUrl(ROOT)
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()
//    }

//    another approach

    private val retrofit: Retrofit
    init{
        retrofit = Retrofit.Builder()
        .baseUrl(ROOT)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    }


    fun <S> createRestService(service: Class<S>): S{
        return retrofit.create(service)
    }

    fun getMovieApiService(): MovieService{
        return createRestService(MovieService::class.java)
    }
}