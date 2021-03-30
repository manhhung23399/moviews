package com.example.moviews.screen.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.data.model.Genre
import kotlinx.android.synthetic.main.item_genres.view.*

class GenresViewHolder(
    itemView: View,
    private val onClickItem: (genre: Genre) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private var genre: Genre? = null

    init {
        itemView.setOnClickListener {
            genre?.let { onClickItem(it) }
        }
    }

    fun bindViewData(genre: Genre) {
        this.genre = genre
        itemView.chipGenres.text = genre.name
    }
}
