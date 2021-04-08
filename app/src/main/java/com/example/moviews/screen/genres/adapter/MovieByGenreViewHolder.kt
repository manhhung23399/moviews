package com.example.moviews.screen.genres.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.data.model.Movie
import com.example.moviews.utils.BaseUrl
import com.example.moviews.utils.loadImage
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieByGenreViewHolder(
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
            textTitle.text = movie.title
            imageMovie.loadImage(BaseUrl.baseUrlImage(movie.backdrop))
        }
    }
}
