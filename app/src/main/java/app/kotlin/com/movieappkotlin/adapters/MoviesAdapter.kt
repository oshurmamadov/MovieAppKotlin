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
import app.kotlin.com.movieappkotlin.models.Movie
import app.kotlin.com.movieappkotlin.utils.MAX_RATING
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

/**
 * Created by parviz on 7/16/16.
 */

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private var items: List<Movie>

    constructor(items: List<Movie>){
        this.items = items
    }


    override fun onBindViewHolder(holder: MovieViewHolder?, position: Int) {
        var item = items[position]

        holder!!.name.text = item.title
        holder!!.vote.text = item.vote_average.toString()
        holder!!.rating.rating = (item.vote_average * 10 * MAX_RATING / 100).toFloat()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_movie_list_item , parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.items.size
    }


    class MovieViewHolder: RecyclerView.ViewHolder{
        var image: ImageView
        var cImage: CircleImageView
        var name: TextView
        var vote: TextView
        var rating: RatingBar
        var card: CardView
        var cardHolder: View

        constructor(itemView: View): super(itemView){
            image = itemView.findViewById(R.id.logo) as ImageView
            name = itemView.findViewById(R.id.name) as TextView
            vote = itemView.findViewById(R.id.vote) as TextView
            rating = itemView.findViewById(R.id.rating) as RatingBar
            cImage = itemView.findViewById(R.id.cAvatar) as CircleImageView
            card = itemView.findViewById(R.id.recycler_card) as CardView
            cardHolder = itemView.findViewById(R.id.card_holder)
        }
    }

}