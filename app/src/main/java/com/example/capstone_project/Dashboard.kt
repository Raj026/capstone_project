package com.example.capstone_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        var home:Home_fragment
        var profile : profile_fragment
        var settings : settings_fragment
        var drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationview = findViewById<NavigationView>(R.id.design_navigation_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar3)
        val bottomnav : BottomNavigationView = findViewById(R.id.bottom_nav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction().replace(R.id.container_fragment,Home_fragment()).commit()
       loadFragment(Home_fragment.newinstance())

        bottomnav.setOnItemSelectedListener {
            var fragment : Fragment
            when(it.itemId){
                R.id.home->{
                    fragment = Home_fragment()
                    loadFragment(fragment)
                    true
                }
                R.id.fav->{
                    fragment = profile_fragment()
                    loadFragment(fragment)
                    true
                }
                R.id.search->{
                    fragment = settings_fragment()
                    loadFragment(fragment)
                    true
                }
                else->false
            }
        }




        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.nav_drawer_open,
            R.string.nav_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()



    }

    private fun loadFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment,fragment)
            .commit()

    }

    private fun replaceFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment,fragment)
            .commit()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}