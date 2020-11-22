package com.tmdb.movies.ui.movieDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tmdb.movies.BuildConfig
import com.tmdb.movies.R
import com.tmdb.movies.data.models.movieDetails.MovieDetails
import com.tmdb.movies.helpers.extensions.*
import com.tmdb.movies.viewModels.MovieDetailsViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 */
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModel()
    private lateinit var thisView: View
    private var movieId: Int = 0

    companion object {
        const val REQUEST_MOVIE_ID = "MovieId"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        thisView = inflater.inflate(R.layout.fragment_movie_details, container, false)

        arguments?.let { it.getInt(REQUEST_MOVIE_ID).let { id -> movieId = id } }
        observeData()
        return thisView
    }

    /**
     *
     */
    private fun observeData() {
        viewModel.output.loading.observe(viewLifecycleOwner, {
            thisView.progress_circular_details.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.output.complete.observe(viewLifecycleOwner, {
            thisView.progress_circular_details.gone()
            displayData(it)
        })

        viewModel.output.error.observe(viewLifecycleOwner, {
            thisView.error.visible()
            thisView.error.text = it
        })
        viewModel.getMovieDetails(movieId)
    }

    private fun displayData(movieDetails: MovieDetails) {
        thisView.title.text = movieDetails.title
        thisView.release_date.text = movieDetails.releaseDate
        thisView.description.text = movieDetails.overview
        thisView.runtime.text = String.format("%s min", movieDetails.runtime)
        thisView.image.loadImage("${BuildConfig.POSTER_BASE_URL}${movieDetails.posterPath}")
        activity?.let { (it as MovieDetailsActivity).updateTitle(movieDetails.title) }
        thisView.genre.text = movieDetails.genres.genreToCommaSeparatedString()
        thisView.language.text = movieDetails.languages.languageToCommaSeparatedString()

        thisView.bookNow.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("${BuildConfig.MOVIE_WEB_URL}${movieId}")
            startActivity(openURL)
        }
    }

}