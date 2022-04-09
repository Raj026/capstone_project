package com.example.capstone_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signup = findViewById<Button>(R.id.button)

        signup.setOnClickListener {
            val intent = Intent(this@MainActivity,Signup::class.java)
            startActivity(intent)
        }
    }
}