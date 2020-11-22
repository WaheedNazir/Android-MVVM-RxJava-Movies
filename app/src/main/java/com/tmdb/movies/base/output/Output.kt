package com.tmdb.movies.base.output

import androidx.lifecycle.MutableLiveData

open class Output<S, E>: BaseOutput<E>() {
    val success by lazy { MutableLiveData<S>() }
}