package com.example.moviews.screen.moviedetail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.data.model.Company
import com.example.moviews.utils.Constant
import com.example.moviews.utils.loadImage
import kotlinx.android.synthetic.main.item_recommend_company.view.*

class CompanyViewHolder(
    itemView: View,
    private val onClickItem: (company: Company) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var company: Company? = null

    init {
        itemView.setOnClickListener {
            company?.let(onClickItem)
        }
    }

    fun bindViewData(company: Company) {
        this.company = company
        itemView.apply {
            textName.text = company.name
            image.loadImage(Constant.BASE_URL_IMAGE + company.logo)
        }
    }
}
