package com.example.capstone_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkIfAdmin()
        val signup = findViewById<Button>(R.id.signup_btn)
        signup.setOnClickListener {
            Intent(this, Signup::class.java).apply {
                startActivity(this)
                finish()
            }
        }
        val signIn = findViewById<Button>(R.id.login_bt)
        signIn.setOnClickListener{
            checkUser()
        }
    }

    private fun checkUser(){
        val email = login_email_et.text.toString()
        val password = login_pass_et.text.toString()
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Username or password cant be empty", Toast.LENGTH_LONG).show()
        } else {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    checkIfAdmin()
                }else {
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun checkIfAdmin(){
        if(auth.currentUser?.uid != null){
            FirebaseFirestore.getInstance().collection("user")
                .document(auth.currentUser!!.uid) .get().addOnSuccessListener { documentSnapshot ->
                    val user = documentSnapshot.toObject(User::class.java)

                    Log.d("MainActivity: ", user.toString())
                    Log.d("MainActivity: ", auth.currentUser!!.uid)
                    if(user?.isAdmin == true){
                        Intent(this@MainActivity, AdminActivity::class.java).apply {
                            startActivity(this)
                            finish()
                        }
                    } else{
                        Intent(this@MainActivity, Dashboard::class.java).apply {
                            startActivity(this)
                            finish()
                        }
                    }
                }
        }
    }
}