package com.example.moviews.screen.genres.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.R
import com.example.moviews.data.model.Movie

class MovieByGenreAdapter(
    private val onClickItem: (Movie) -> Unit
) : RecyclerView.Adapter<MovieByGenreViewHolder>() {

    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieByGenreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)
        return MovieByGenreViewHolder(view, onClickItem)
    }

    override fun onBindViewHolder(holder: MovieByGenreViewHolder, position: Int) {
        holder.bindViewData(movies[position])
    }

    override fun getItemCount() = movies.size

    fun updateData(movies: MutableList<Movie>?) {
        movies?.let {
            this.movies.clear()
            this.movies.addAll(movies)
            notifyDataSetChanged()
        }
    }
}
