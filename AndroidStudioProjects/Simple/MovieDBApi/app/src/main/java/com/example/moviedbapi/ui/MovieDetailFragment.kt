package com.example.moviedbapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

import com.example.moviedbapi.R
import com.example.moviedbapi.adapter.NowplayingAdapter
import com.example.moviedbapi.viewmodel.NowplayingViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import kotlinx.android.synthetic.main.fragment_movie_detail.view.detail_title
import kotlinx.android.synthetic.main.now_playing.view.*

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : Fragment() {
//    private var nowplayingAdapter : NowplayingAdapter = NowplayingAdapter()
    lateinit var nowplayingViewModel: NowplayingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowplayingViewModel = ViewModelProviders.of(this).get(NowplayingViewModel::class.java)
        nowplayingViewModel.getDetailMovie().observe(viewLifecycleOwner, Observer {
            detail_title.text = it.title
            val base_url = "https://image.tmdb.org/t/p/w500"

            (activity as AppCompatActivity).supportActionBar?.title = it.title
             release_movie.text = it.release_date
             run_time.text = it.runtime.toString()
             director.text = it.original_title
            budget.text = it.budget.toString()
            revenue.text = it.revenue.toString()
            overview.text = it.overview



            Picasso.get()
                .load("$base_url${it.poster_path}")
                .placeholder(R.drawable.money)
                .into(detail_image)
                Log.d("video","${it.homepage}")
            trailer.loadUrl(it.homepage)
        })
    }

    override fun onResume() {
        super.onResume()
        var messageArgs =arguments?.let { MovieDetailFragmentArgs.fromBundle(it) }
        var movie_id = messageArgs?.searchById
        Log.d("movie","$movie_id")
        movie_id?.let { nowplayingViewModel.setDetailMovie(it,"4f0ce3f039896ac1b5ceda2daa253f7f") }

    }

}
