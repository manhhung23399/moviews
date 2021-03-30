package com.example.moviews.screen.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.R
import com.example.moviews.data.model.Genre

class GenresAdapter(
    private val onClickItem: (genre: Genre) -> Unit
) : RecyclerView.Adapter<GenresViewHolder>() {

    private val genres = mutableListOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genres, parent, false)
        return GenresViewHolder(view, onClickItem)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bindViewData(genres[position])
    }

    override fun getItemCount(): Int = genres.size

    fun updateData(genres: MutableList<Genre>?) {
        genres?.let {
            this.genres.clear()
            this.genres.addAll(genres)
            notifyDataSetChanged()
        }
    }
}
