package com.example.mobilevynils.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController

import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.fragment.app.Fragment
import com.example.mobilevynils.AlbumFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav = findViewById(R.id.nav_view) as BottomNavigationView
        loadFragment(AlbumFragment())
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_albums -> {
                    loadFragment(AlbumFragment())
                    true
                }
                R.id.navigation_artists -> {
                    loadFragment(ArtistaFragment())
                    true
                }

                else -> {
                    loadFragment(AlbumFragment())
                    true
                }
            }
        }

    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}