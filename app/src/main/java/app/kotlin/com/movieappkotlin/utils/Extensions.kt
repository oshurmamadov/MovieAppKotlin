package app.kotlin.com.movieappkotlin.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.kotlin.com.movieappkotlin.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by parviz on 7/17/16.
 */

fun ViewGroup.myCustomViewInflater(resLayoutID: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resLayoutID, this, attachToRoot)
}

fun ImageView.loadImageByPicasso(url: String){
    Picasso.with(context).load(url).into(this,object: Callback {
        override fun onSuccess() {

        }
        override fun onError() {
            Log.e("App Error","Picasso image loading error")
            (this as ImageView).setImageResource(R.drawable.no_avatar)
        }
    })
}