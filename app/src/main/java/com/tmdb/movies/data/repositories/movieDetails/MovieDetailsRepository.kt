package com.tmdb.movies.data.repositories.movieDetails

import com.tmdb.movies.base.output.SimpleOutput
import com.tmdb.movies.data.api.ApiServices
import com.tmdb.movies.data.models.movieDetails.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class MovieDetailsRepository constructor(private val apiServices: ApiServices) {


    var output = SimpleOutput<MovieDetails, String>()

    /**
     * Get Movie Details Api call
     */
    fun getMovieDetails(compositeDisposable: CompositeDisposable, movieId: Int) {
        loading(true)
        try {
            compositeDisposable.add(
                apiServices.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            completed(it)
                        }, {
                            error(it.message)
                        })
            )
        } catch (e: Exception) {
            error(e.localizedMessage)
        }
    }

    /**
     *
     */
    private fun loading(load: Boolean) {
        output.loading.postValue(load)
    }

    /**
     *
     */
    private fun completed(movieDetails: MovieDetails) {
        output.complete.postValue(movieDetails)
        loading(false)
    }

    /**
     *
     */
    private fun error(error: String?) {
        error?.let {
            Timber.e("Error: $it")
            output.error.postValue(it)
        }
        loading(false)
    }
}