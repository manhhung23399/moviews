package com.example.moviews.screen.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.R
import com.example.moviews.data.model.Movie

class TopSearchAdapter(
    private val onClickItem: (Movie) -> Unit
) : RecyclerView.Adapter<TopSearchViewHolder>() {

    private val topSearches = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_search, parent, false)
        return TopSearchViewHolder(view, onClickItem)
    }

    override fun onBindViewHolder(holder: TopSearchViewHolder, position: Int) {
        holder.bindViewData(topSearches[position])
    }

    override fun getItemCount() = topSearches.size

    fun updateData(movies: MutableList<Movie>?) {
        movies?.let {
            topSearches.clear()
            topSearches.addAll(movies)
            notifyDataSetChanged()
        }
    }
}
