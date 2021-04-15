package com.example.moviews

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.moviews.screen.error.ErrorFragment
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
    private val errorFragment: ErrorFragment = ErrorFragment.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragment(homeFragment)
        eventClickBottomNavigationView()
        checkInternet()
    }

    private fun eventClickBottomNavigationView() {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationHome -> {
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    switchFragment(homeFragment)
                }
                R.id.navigationSearch -> {
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    switchFragment(searchFragment)
                }
                R.id.navigationFavorites -> {
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    switchFragment(favoriteFragment)
                }
                R.id.navigationInfomation -> {
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    switchFragment(informationFragment)
                }
            }
            true
        }
    }

    private fun checkInternet() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                    }

                    override fun onLost(network: Network) {
                        switchFragment(errorFragment)
                    }
                })
            }
        }
    }
}
