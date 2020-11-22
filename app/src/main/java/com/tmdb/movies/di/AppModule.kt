package com.tmdb.movies.di

import com.tmdb.movies.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.tmdb.movies.data.api.ApiServices
import com.tmdb.movies.data.repositories.movieDetails.MovieDetailsRepository
import com.tmdb.movies.data.repositories.movies.MoviesRepository
import com.tmdb.movies.viewModels.MoviesViewModel
import com.tmdb.movies.viewModels.MovieDetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val repositoriesModule = module {

    fun provideMoviesRepository(apiServices: ApiServices): MoviesRepository =
        MoviesRepository(apiServices)

    single { provideMoviesRepository(get()) }

    fun provideMovieDetailsRepository(apiServices: ApiServices): MovieDetailsRepository =
        MovieDetailsRepository(apiServices)

    single { provideMovieDetailsRepository(get()) }
}

val viewModelModule = module {

    fun provideMoviesViewModel(repository: MoviesRepository): MoviesViewModel =
        MoviesViewModel(repository)

    fun provideMovieDetailsViewModel(repository: MovieDetailsRepository): MovieDetailsViewModel =
        MovieDetailsViewModel(repository)

    viewModel { provideMoviesViewModel(get()) }

    viewModel { provideMovieDetailsViewModel(get()) }
}


val networkModules = module {

    single<ApiServices> {
        get<Retrofit>().create(ApiServices::class.java)
    }
    val connectTimeout: Long = 40
    val readTimeout: Long = 40

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }

    single {
        provideRetrofit(get(), BuildConfig.BASE_URL)
    }

}

val allModules = repositoriesModule + viewModelModule + networkModules