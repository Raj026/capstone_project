package com.example.capstone_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import android.support.v7.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone_project.constants.extras.adminPassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Signup : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var userCollection = FirebaseFirestore.getInstance().collection("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val Signup_btn = findViewById<Button>(R.id.signup_signup_btn)
        auth = FirebaseAuth.getInstance()
        Signup_btn.setOnClickListener {
            val user = checkFields()
            Log.d("this@Signup", user.toString())
            if (user != null){
                if(signup_password_et.text.toString() == adminPassword)
                    user.isAdmin = true
                registerUser(user)
            }
        }
    }

    private fun checkFields(): User? {
        val firstName = signup_firstname_et.text.toString()
        val lastName = signup_lastname_et.text.toString()
        val email = signup_email_et.text.toString()
        val contact = signup_contact_et.text.toString()
        val institute = signup_institute_et.text.toString()
        val password = signup_password_et.text.toString()
        val confirmPassword = signup_confirm_et.text.toString()
        var user:User? = null

        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || contact.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_LONG).show()
        } else if (confirmPassword != password){
            Toast.makeText(this, "Unable to confirm password", Toast.LENGTH_LONG).show()
        } else{
            user = User(
                firstName = firstName,
                lastName = lastName,
                contact = contact,
                email = email,
                institute = institute,
            )
        }
        return user
    }

    private fun registerUser(user: User) {
        val signup_email = findViewById<EditText>(R.id.signup_email_et)
        val signup_password = findViewById<EditText>(R.id.signup_password_et)
        val email = signup_email.text.toString()
        val password = signup_password.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->
                        if(task.isSuccessful){
                            user.uid = auth.currentUser?.uid.toString()
                            userCollection.document(user.uid).set(user)
                            if(user.isAdmin){
                                Intent(this@Signup, AdminActivity::class.java).also {
                                    startActivity(it)
                                    finish()
                                }
                            } else {
                                Intent(this@Signup, Dashboard::class.java).also {
                                    startActivity(it)
                                    finish()
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@Signup, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun checkLoggedInState() {
        if (auth.currentUser == null) {
            Toast.makeText(this, "You're not signed in ", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "You're signed in as: ${auth.currentUser}", Toast.LENGTH_LONG).show()
        }
    }
}