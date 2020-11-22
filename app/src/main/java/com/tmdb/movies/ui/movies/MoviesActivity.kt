package com.tmdb.movies.ui.movies

import android.os.Bundle
import com.tmdb.movies.R
import com.tmdb.movies.base.BaseActivity

class MoviesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(R.id.content, MoviesFragment(), true)
    }
}