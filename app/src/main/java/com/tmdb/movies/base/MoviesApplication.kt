package com.tmdb.movies.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.tmdb.movies.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


const val FIRST_PAGE = 1
const val MOVIES_PER_PAGE = 20

class MoviesApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        var context: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        configureKoin()
    }

    private fun configureKoin() {
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MoviesApplication)
            modules(allModules)
        }
    }
}