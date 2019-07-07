package dev.nxonxon.exampleview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import dev.nxonxon.exampleview.home.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var idMenuSelected: Int = R.id.navigationHome
    val homeFragment by lazy {
        HomeFragment()
    }
    val searchFragment by lazy {
        SearchFragment()
    }
    val newPostFragment by lazy {
        NewPostFragment()
    }
    val favoriteFragment by lazy {
        FavoriteFragment()
    }
    val userFragment by lazy {
        UserFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frmMain, HomeFragment())
            .commit()

        botNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationHome -> {
                    if (idMenuSelected != R.id.navigationHome) {
                        idMenuSelected = R.id.navigationHome
                        showFragment(homeFragment)
                        Log.d("navigationHome", "Selected")
                    }
                    true
                }
                R.id.navigationSearch -> {
                    if (idMenuSelected != R.id.navigationSearch) {
                        idMenuSelected = R.id.navigationSearch
                        showFragment(searchFragment)
                    }
                    true
                }
                R.id.navigationNewPost -> {
                    if (idMenuSelected != R.id.navigationNewPost) {
                        idMenuSelected = R.id.navigationNewPost
                        showFragment(newPostFragment)
                    }
                    true
                }
                R.id.navigationFavorite -> {
                    if (idMenuSelected != R.id.navigationFavorite) {
                        idMenuSelected = R.id.navigationFavorite
                        showFragment(favoriteFragment)
                    }
                    true
                }
                R.id.navigationUser -> {
                    if (idMenuSelected != R.id.navigationUser) {
                        idMenuSelected = R.id.navigationUser
                        showFragment(userFragment)
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frmMain, fragment)
            .commit()
    }

}