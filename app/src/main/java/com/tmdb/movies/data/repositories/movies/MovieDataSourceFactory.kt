package com.tmdb.movies.data.repositories.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.tmdb.movies.base.output.SimpleOutput
import com.tmdb.movies.data.api.ApiServices
import com.tmdb.movies.data.models.movieDetails.MovieDetails
import com.tmdb.movies.data.models.movies.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory(
    private val apiService: ApiServices,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    private val moviesLiveDataSource = MutableLiveData<MovieDataSource>()
    var output = SimpleOutput<MovieDetails, String>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(apiService, compositeDisposable)
        output = movieDataSource.output
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }

}