package com.example.moviews.screen.moviedetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviews.BuildConfig
import com.example.moviews.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_trailer.*

class TrailerActivity : YouTubeBaseActivity() {
    var init: YouTubePlayer.OnInitializedListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)
        intent.getStringExtra("KEY")?.let { initYoutube(it) }
        imageBackTrailer.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initYoutube(string: String) {
        init = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(string)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
            }
        }
        viewYoutube.initialize("AIzaSyDmD_pG46vG_THihD7W0DwvXKGu65tgrI4", init)
    }
    companion object{
        fun getIntent(context:Context) = Intent(context,TrailerActivity::class.java)
    }
}