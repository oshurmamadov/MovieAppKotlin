package app.kotlin.com.movieappkotlin.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by parviz on 7/17/16.
 */

fun ViewGroup.myCustomViewInflater(resLayoutID: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resLayoutID, this, attachToRoot)
}