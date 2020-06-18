package com.example.moviedbapi.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapi.R
import com.example.moviedbapi.adapter.NowplayingAdapter
import com.example.moviedbapi.model.nowPlaying.NowPlayingResult
import com.example.moviedbapi.viewmodel.NowplayingViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(),NowplayingAdapter.ClickListener {

      lateinit var nowplayingViewModel: NowplayingViewModel
    private lateinit var viewManager : RecyclerView.LayoutManager
    private lateinit var nowplayingAdapter: NowplayingAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return  inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        nowplayingAdapter =  NowplayingAdapter()
        now_recycler.adapter = nowplayingAdapter
        now_recycler.layoutManager = viewManager
        nowplayingAdapter.setOnClickListener(this)
        obserViewModel()
    }

    fun obserViewModel(){
        nowplayingViewModel = ViewModelProviders.of(this)
            .get(NowplayingViewModel::class.java)

        nowplayingViewModel.getResult().observe(viewLifecycleOwner, Observer { result ->
            result?.results?.let { nowplayingAdapter.updateResult(it) }
        })
        }

    override fun onResume() {
        super.onResume()
        nowplayingViewModel.loadResult()
    }

    override fun onClick(detail: NowPlayingResult) {
//        nowplayingViewModel.setDetailMovie(detail.id)
        Log.d("moviedetail","${detail.id}")
        var action = HomeFragmentDirections.actionNavHomeToMovieDetailFragment(detail.id)
        findNavController().navigate(action)

    }


}

