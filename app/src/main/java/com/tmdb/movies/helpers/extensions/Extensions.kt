package com.tmdb.movies.helpers.extensions

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.tmdb.movies.data.models.movieDetails.Genre
import com.tmdb.movies.data.models.movieDetails.Language

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

/**
 * Easy toast function for Activity.
 */
fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

/**
 * List extension to get comma separated values for Genre
 */
fun List<Genre>.genreToCommaSeparatedString(): String {
    return this.joinToString(", ", transform = { it.name })
}

/**
 * List extension to get comma separated values for SpokenLanguage
 */
fun List<Language>.languageToCommaSeparatedString(): String {
    return this.joinToString(", ", transform = { it.name })
}