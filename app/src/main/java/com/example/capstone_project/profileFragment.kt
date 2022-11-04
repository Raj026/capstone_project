package com.example.capstone_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.*

class profileFragment : Fragment(R.layout.fragment_profile) {

    val auth = FirebaseAuth.getInstance()
    var userCollection = FirebaseFirestore.getInstance().collection("user")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var user: User? = null

        userCollection.document(auth.currentUser!!.uid).get()
            .addOnSuccessListener { documentSnapshot ->
                user = documentSnapshot.toObject(User::class.java)

                profile_firstname_tv.setText(user?.firstName)
                profile_lastname_tv.setText(user?.lastName)
                profile_contact_tv.setText(user?.contact)
                profile_email_tv.setText(user?.email)
                profile_ints_tv.setText(user?.institute)

            }

        profile_logout_bt.setOnClickListener {
            auth.signOut()
            Intent(requireActivity(), MainActivity::class.java).also {
                startActivity(it)
                requireActivity().finish()
            }
        }
    }
}