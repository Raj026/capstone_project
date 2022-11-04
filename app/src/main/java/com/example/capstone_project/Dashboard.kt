package com.example.capstone_project

//import android.widget.Toolbar
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class
Dashboard : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        var home: HomeFragment
        var profile: profileFragment
        var settings: settings_fragment
        var drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationview = findViewById<NavigationView>(R.id.design_navigation_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar3)
        val bottomnav: BottomNavigationView = findViewById(R.id.bottom_nav)


        navigationview.setNavigationItemSelectedListener {
            if (it.itemId == R.id.nav_profile){
                val intent = Intent(this, AdminActivity::class.java)
                this.startActivity(intent)
            }
            return@setNavigationItemSelectedListener true
        }

        val addThread = findViewById<FloatingActionButton>(R.id.add_threads_dashboard)
        addThread.setOnClickListener {
            val intent = Intent(this, AddThread::class.java)
            startActivity(intent)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.container_fragment, HomeFragment())
            .commit()
        loadFragment(HomeFragment.newinstance())


        bottomnav.setOnItemSelectedListener {
            var fragment: Fragment
            when (it.itemId) {
                R.id.home -> {
                    fragment = HomeFragment()
                    addThread.isVisible = true
                    loadFragment(fragment)
                    true
                }
                R.id.fav -> {
                    fragment = FavFragment()
                    addThread.isVisible = true
                    loadFragment(fragment)
                    true
                }
                R.id.search -> {
                    fragment = settings_fragment()
                    addThread.isVisible = false
                    loadFragment(fragment)
                    true
                }

                R.id.profile -> {
                    fragment = profileFragment()
                    addThread.isVisible = false
                    loadFragment(fragment)
                    true
                }

                else -> false
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
            .replace(R.id.container_fragment, fragment)
            .commit()

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val intent = Intent(this, AdminActivity::class.java)
                this.startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.nav_profile) {
            val intent = Intent(this, AdminActivity::class.java)
            this.startActivity(intent)
        }
        return true
    }


}




