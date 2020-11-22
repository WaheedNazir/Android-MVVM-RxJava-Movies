package com.tmdb.movies.viewModels

import androidx.lifecycle.ViewModel
import com.tmdb.movies.base.output.SimpleOutput
import com.tmdb.movies.data.models.movieDetails.MovieDetails
import com.tmdb.movies.data.repositories.movieDetails.MovieDetailsRepository
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsViewModel(private val repository: MovieDetailsRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var output = SimpleOutput<MovieDetails, String>()

    fun getMovieDetails(movieId: Int) {
        repository.getMovieDetails(compositeDisposable, movieId)
    }

    init {
        output = repository.output
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}