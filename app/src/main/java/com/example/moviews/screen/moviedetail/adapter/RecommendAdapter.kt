package com.example.moviews.screen.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.R
import com.example.moviews.data.model.Movie

class RecommendAdapter(
    private val onClickItem: (Movie) -> Unit
) : RecyclerView.Adapter<RecommendViewHolder>() {

    private val recommendations = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommend_company, parent, false)
        return RecommendViewHolder(view, onClickItem)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.bindViewData(recommendations[position])
    }

    override fun getItemCount() = recommendations.size

    fun updateData(recommendations: MutableList<Movie>?) {
        recommendations?.let {
            this.recommendations.clear()
            this.recommendations.addAll(recommendations)
            notifyDataSetChanged()
        }
    }
}
