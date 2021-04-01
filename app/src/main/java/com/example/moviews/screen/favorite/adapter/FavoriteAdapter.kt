package com.example.moviews.screen.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.R
import com.example.moviews.data.model.Movie

class FavoriteAdapter(
    private val onClickItem: (movie: Movie) -> Unit,
    private val onClickDelete: (movie: Movie) -> Unit
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    private var favoriteMovies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_favorite, parent, false)
        return FavoriteViewHolder(view, onClickItem, onClickDelete)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindViewData(favoriteMovies[position])
    }

    override fun getItemCount() = favoriteMovies.size

    fun updateData(movies: MutableList<Movie>?) {
        movies?.let {
            favoriteMovies.clear()
            favoriteMovies.addAll(movies)
            notifyDataSetChanged()
        }
    }
}
