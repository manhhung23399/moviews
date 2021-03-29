package com.example.moviews.screen.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviews.data.model.Movie
import com.example.moviews.utils.Constant
import com.example.moviews.utils.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(
    itemView: View,
    private val onClickItem: (movie: Movie) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var movie: Movie? = null

    init {
        itemView.setOnClickListener {
            movie?.let { onClickItem(it) }
        }
    }

    fun bindViewData(movie: Movie) {
        this.movie = movie
        itemView.apply {
            textTitleMovie.text = movie.title
            textVote.text = movie.vote.toString()
            imageMovie.loadImage(Constant.BASE_URL_IMAGE+movie.poster,imageMovie)
        }
    }

}
