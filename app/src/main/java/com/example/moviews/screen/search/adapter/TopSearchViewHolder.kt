package com.example.moviews.screen.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.data.model.Movie
import com.example.moviews.utils.Constant
import com.example.moviews.utils.loadImage
import kotlinx.android.synthetic.main.item_movie_search.view.*

class TopSearchViewHolder(
    itemView: View,
    private val onClickItem: (movie: Movie) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var movie: Movie? = null

    init {
        itemView.setOnClickListener {
            movie?.let(onClickItem)
        }
    }

    fun bindViewData(movie: Movie) {
        this.movie = movie
        itemView.apply {
            textTitleMovieSearch.text = movie.title
            imageMovieSearch.loadImage(Constant.BASE_URL_IMAGE + movie.backdrop)
        }
    }
}
