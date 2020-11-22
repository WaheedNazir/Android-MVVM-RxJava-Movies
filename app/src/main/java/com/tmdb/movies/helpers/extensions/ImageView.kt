package com.tmdb.movies.helpers.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tmdb.movies.R

fun ImageView.loadImage(url: String, placeHolder: Int = R.mipmap.ic_launcher) {
    if (url.isEmpty()) {
        setImageResource(placeHolder)
        return
    }
    val options = RequestOptions()
        .centerCrop()
        .placeholder(placeHolder)
        .error(placeHolder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
    Glide.with(this).load(url).apply(options).into(this)
}
