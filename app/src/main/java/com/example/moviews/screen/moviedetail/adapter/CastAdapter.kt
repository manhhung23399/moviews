package com.example.moviews.screen.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.R
import com.example.moviews.data.model.Cast

class CastAdapter(
    private val onClickItem: (Cast) -> Unit
) : RecyclerView.Adapter<CastViewHolder>() {

    private val casts = mutableListOf<Cast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cast, parent, false)
        return CastViewHolder(view, onClickItem)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bindViewData(casts[position])
    }

    override fun getItemCount() = casts.size

    fun updateData(casts: MutableList<Cast>?) {
        casts?.let {
            this.casts.clear()
            this.casts.addAll(casts)
            notifyDataSetChanged()
        }
    }
}
