package com.example.moviedbapi.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviedbapi.api.MovieApi
import com.example.moviedbapi.model.detailMovie.DetailMovie
import com.example.moviedbapi.model.detailMovie.Movies
import com.example.moviedbapi.model.nowPlaying.NowPlaying
import com.example.moviedbapi.model.nowPlaying.NowPlayingResult

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowplayingViewModel(application: Application) : AndroidViewModel(application) {
    var result : MutableLiveData<NowPlaying> = MutableLiveData()
    var detailMovie: MutableLiveData<Movies> = MutableLiveData()
    var loading : MutableLiveData<Boolean> = MutableLiveData()
    var error : MutableLiveData<Boolean> = MutableLiveData()
    private var nowplayingApi : MovieApi = MovieApi()

    fun getResult() : LiveData<NowPlaying> = result

    fun getDetailMovie() : LiveData<Movies> = detailMovie

    fun getLoading() : LiveData<Boolean> = loading

    fun getError() : LiveData<Boolean> = error

    fun loadResult(){
        val call = nowplayingApi.getNowplaying()
        call.enqueue(object : Callback<NowPlaying>{
            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
//                error.value = true
//                loading.value = false
            }

            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
//                loading.value = false
                val resultList = NowPlaying( response?.body()?.results?: emptyList())
                result.value = resultList

                Log.d("NowResult", resultList.toString())
            }


        })
    }
//    fun setDetailMovie(movie: NowPlayingResult){
//        detailMovie.value = movie
//        Log.d("sithu","$movie")
//    }
    fun setDetailMovie(movieId: Int,apiKey : String){
        val call_detail = nowplayingApi.getMovieDetail(movieId,apiKey)
        call_detail.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val detailList  =  response.body()
                Log.d("detaillist","$detailList")
                detailMovie.value = detailList
            }

        })
    }
}