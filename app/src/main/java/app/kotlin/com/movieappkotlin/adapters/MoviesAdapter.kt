package app.kotlin.com.movieappkotlin.adapters

import android.support.v7.widget.CardView
import app.kotlin.com.movieappkotlin.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import app.kotlin.com.movieappkotlin.models.Images
import app.kotlin.com.movieappkotlin.models.Movie
import app.kotlin.com.movieappkotlin.utils.MAX_RATING
import app.kotlin.com.movieappkotlin.utils.loadImageByPicasso
import app.kotlin.com.movieappkotlin.utils.myCustomViewInflater
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.custom_movie_list_item.view.*
import java.util.*

/**
 * Created by parviz on 7/16/16.
 */

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private var items: List<Movie>
    private var images: Images

    constructor(items: List<Movie>,images: Images){
        this.items = items
        this.images = images
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = parent.myCustomViewInflater(R.layout.custom_movie_list_item)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindViewHolder(items[position],images)
    }

    override fun getItemCount(): Int {
        return this.items.size
    }


    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindViewHolder(item: Movie,images:Images) = with(itemView){
            name.text = item.title
            vote.text = item.vote_average.toString()
            rating.rating = (item.vote_average * 10 * MAX_RATING / 100).toFloat()
            logo.loadImageByPicasso(images.base_url + images.backdrop_sizes[1] + item.backdrop_path)
            cAvatar.loadImageByPicasso(images.base_url + images.backdrop_sizes[1]  + item.poster_path)
        }
    }

}