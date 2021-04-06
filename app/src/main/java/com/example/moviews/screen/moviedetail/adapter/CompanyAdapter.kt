package com.example.moviews.screen.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviews.R
import com.example.moviews.data.model.Company

class CompanyAdapter(
    private val onClickItem: (Company) -> Unit
) : RecyclerView.Adapter<CompanyViewHolder>() {

    private val companies = mutableListOf<Company>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommend_company, parent, false)
        return CompanyViewHolder(view, onClickItem)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindViewData(companies[position])
    }

    override fun getItemCount() = companies.size

    fun updateData(companies: MutableList<Company>?) {
        companies?.let {
            this.companies.clear()
            this.companies.addAll(companies)
            notifyDataSetChanged()
        }
    }
}
