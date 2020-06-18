package com.example.moviedbapi.api



import android.util.Log
import com.example.moviedbapi.model.detailMovie.DetailMovie
import com.example.moviedbapi.model.detailMovie.Movies
import com.example.moviedbapi.model.nowPlaying.NowPlaying
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    private val movieApiInterface : MovieInterface

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieApiInterface = retrofit.create(
            MovieInterface::class.java
        )
    }

    fun getNowplaying(): Call<NowPlaying>{
        return movieApiInterface.getNowPlaying()
    }

    fun getMovieDetail(movieId: Int,apiKey : String): Call<Movies>{

        return movieApiInterface.getMovieDetail(movieId,apiKey)
    }
}