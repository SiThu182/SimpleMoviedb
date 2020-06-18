package com.example.moviedbapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapi.R
import com.example.moviedbapi.model.nowPlaying.NowPlaying
import com.example.moviedbapi.model.nowPlaying.NowPlayingResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.now_playing.view.*

class NowplayingAdapter(var nowPlaying: List<NowPlayingResult> = ArrayList()):RecyclerView.Adapter<NowplayingAdapter.NowplayingViewHolder>() {
    var mClickListener : ClickListener? = null

    interface ClickListener{
        fun onClick(detail: NowPlayingResult)
    }

    fun setOnClickListener(clickLIstener: ClickListener){
        this.mClickListener = clickLIstener
    }
    fun updateResult(nowPlaying: List<NowPlayingResult>)
    {
        this.nowPlaying = nowPlaying
        notifyDataSetChanged()
    }

    inner class NowplayingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) , View.OnClickListener{
            private lateinit var nowPlaying : NowPlayingResult
            private var view : View = itemView
            init {
                view.setOnClickListener(this)
            }
        fun bindNowplaying(nowPlaying: NowPlayingResult){
            this.nowPlaying = nowPlaying
            var img = nowPlaying.poster_path
            Picasso.get().load("https://image.tmdb.org/t/p/w200$img").into(itemView.movie_image)
            itemView.title.text = nowPlaying.title
        }

        override fun onClick(v: View){
            mClickListener?.onClick(nowPlaying)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowplayingViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.now_playing,parent,false)
        return NowplayingViewHolder(view)
    }

    override fun getItemCount(): Int = nowPlaying.size

    override fun onBindViewHolder(holder: NowplayingViewHolder, position: Int) {
        holder.bindNowplaying(nowPlaying[position])
    }


}