package com.example.capstone_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        //val navigationview = findViewById<NavigationView>(R.id.design_navigation_view)
        val toolbar:Toolbar  = findViewById(R.id.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setSupportActionBar(toolbar)

        var toggle  = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }



}