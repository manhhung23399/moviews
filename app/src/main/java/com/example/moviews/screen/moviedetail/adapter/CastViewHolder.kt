package com.example.moviews.screen.moviedetail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.data.model.Cast
import com.example.moviews.utils.Constant
import com.example.moviews.utils.loadImage
import kotlinx.android.synthetic.main.item_cast.view.*

class CastViewHolder(
    itemView: View,
    private val onClickItem: (casts: Cast) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var cast: Cast? = null

    init {
        itemView.setOnClickListener {
            cast?.let(onClickItem)
        }
    }

    fun bindViewData(cast: Cast) {
        this.cast = cast
        itemView.apply {
            textNameCast.text = cast.name
            imageCast.loadImage(Constant.BASE_URL_IMAGE + cast.profilePath)
        }
    }
}
