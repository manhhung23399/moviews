package com.example.moviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviews.screen.favorite.FavoriteFragment
import com.example.moviews.screen.home.HomeFragment
import com.example.moviews.screen.information.InformationFragment
import com.example.moviews.screen.search.SearchFragment
import com.example.moviews.utils.switchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeFragment: HomeFragment = HomeFragment.newInstance()
    private val favoriteFragment: FavoriteFragment = FavoriteFragment.newInstance()
    private val informationFragment: InformationFragment = InformationFragment.newInstance()
    private val searchFragment: SearchFragment = SearchFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragment(homeFragment)
        eventClickBottomNavigationView()
    }

    private fun eventClickBottomNavigationView() {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationHome -> {
                    switchFragment(homeFragment)
                }
                R.id.navigationSearch -> {
                    switchFragment(searchFragment)
                }
                R.id.navigationFavorites -> {
                    switchFragment(favoriteFragment)
                }
                R.id.navigationInfomation -> {
                    switchFragment(informationFragment)
                }
            }
            true
        }
    }
}
