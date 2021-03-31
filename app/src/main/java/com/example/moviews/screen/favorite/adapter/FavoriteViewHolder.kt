package com.example.moviews.screen.favorite.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.data.model.Movie
import com.example.moviews.utils.Constant
import com.example.moviews.utils.loadImage
import kotlinx.android.synthetic.main.item_movie_favorite.view.*

class FavoriteViewHolder(
    itemView: View,
    private val onClickItem: (movie: Movie) -> Unit,
    private val onClickDelete: (movie: Movie) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var movie: Movie? = null

    init {
        itemView.setOnClickListener {
            movie?.let(onClickItem)
        }
        itemView.imageDelete.setOnClickListener {
            movie?.let(onClickDelete)
        }
    }

    fun bindViewData(movie: Movie) {
        this.movie = movie
        itemView.apply {
            textTitleMovieFavorite.text = movie.title
            imageMovieFavorite.loadImage(
                Constant.BASE_URL_IMAGE + movie.backdrop
            )
        }
    }
}
