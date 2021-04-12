package com.example.moviews.screen.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.moviews.R
import com.example.moviews.data.model.Movie
import com.example.moviews.utils.Constant
import com.example.moviews.utils.loadImage

import kotlinx.android.synthetic.main.item_slide.view.*
import java.lang.Math.min

class ViewPagerAdapter(
    private val onClickItem: (Movie) -> Unit
) : PagerAdapter() {
    private val movies = mutableListOf<Movie>()

    override fun getCount() = min(movies.size, 5)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            LayoutInflater.from(container.context).inflate(R.layout.item_slide, container, false)
        view.imageSlide.loadImage(Constant.BASE_URL_IMAGE + movies[position].backdrop)
        view.textSlide.text = movies[position].title
        view.setOnClickListener { onClickItem(movies[position]) }
        container.addView(view, 0)
        return view
    }

    override fun isViewFromObject(view: View, obj: Any) = view == obj

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun updateData(newItems: MutableList<Movie>) {
        movies.apply {
            if (isNotEmpty()) clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }
}
