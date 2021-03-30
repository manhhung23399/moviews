package com.example.moviews.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.R
import com.example.moviews.data.model.Movie

class MovieAdapter(private val onClickItem: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, onClickItem)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindViewData(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun updateData(movies: MutableList<Movie>?) {
        movies?.let {
            this.movies.clear()
            this.movies.addAll(movies)
            notifyDataSetChanged()
        }
    }

}
