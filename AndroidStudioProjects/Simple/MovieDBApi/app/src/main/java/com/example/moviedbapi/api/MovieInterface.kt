package com.example.moviedbapi.api



import com.example.moviedbapi.model.detailMovie.DetailMovie
import com.example.moviedbapi.model.detailMovie.Movies
import com.example.moviedbapi.model.nowPlaying.NowPlaying
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {
    @GET("movie/now_playing?api_key=4f0ce3f039896ac1b5ceda2daa253f7f")
    fun getNowPlaying():Call<NowPlaying>

//    @GET("movie/{movie_id}")
//    fun getMovieDetail(
//        @Path("movie_id") movie_id : Int,
//        @Query( "api_key " ) apiKey : String =  "4f0ce3f039896ac1b5ceda2daa253f7f" ,
//        @Query( "language" ) language : String=  "en-US"
// ):Call<DetailMovie>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey : String

        ): Call<Movies>


}