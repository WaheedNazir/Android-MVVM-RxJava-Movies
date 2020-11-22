package com.tmdb.movies.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdb.movies.BuildConfig
import com.tmdb.movies.R
import com.tmdb.movies.data.models.movies.Movie
import com.tmdb.movies.helpers.extensions.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesPagedAdapter : PagedListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback()) {

    var onMovieClicked: ((Movie?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return MovieItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieItemViewHolder).bind(getItem(position))
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            (oldItem.id == newItem.id)

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            (oldItem == newItem)
    }


    inner class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie?) {
            itemView.title.text = movie?.title
            itemView.release_date.text = movie?.releaseDate
            val voteAverage: Double? = movie?.voteAverage
            itemView.circularProgressBar.progress = voteAverage?.times(10)?.toFloat()!!
            itemView.percentage.text = String.format("%d%s", voteAverage.times(10).toInt(), "%")
            itemView.image.loadImage("${BuildConfig.POSTER_BASE_URL}${movie.posterPath}")
            itemView.setOnClickListener {
                onMovieClicked?.invoke(movie)
            }
        }
    }
}