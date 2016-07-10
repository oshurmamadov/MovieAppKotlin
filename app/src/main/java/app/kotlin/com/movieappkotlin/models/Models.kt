package app.kotlin.com.movieappkotlin.models

/**
 * Created by parviz on 7/10/16.
 */

data class PopularMovie( val results: List<Movie>,
                         val page: Int,
                         val total_pages: Int,
                         val total_results: Int
                        )

data class Movie( val vote_average:      Double,
                  val backdrop_path:     String,
                  val adult:             Boolean,
                  val id:                Int,
                  val title:             String,
                  val original_language: String,
                  val overview:          String,
                  val genre_ids:         IntArray,
                  val original_title:    String,
                  val release_date:      String,
                  val vote_count:        Int,
                  val poster_path:       String,
                  val video:             Boolean,
                  val popularity:        String
                )