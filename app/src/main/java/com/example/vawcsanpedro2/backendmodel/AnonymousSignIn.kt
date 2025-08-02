package com.example.vawcsanpedro2.backendmodel

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

object AnonymousSignIn {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInAnonymously(
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AnonSignIn", "Anonymous sign-in success: ${auth.currentUser?.uid}")
                    onSuccess()
                } else {
                    task.exception?.let {
                        Log.e("AnonSignIn", "Anonymous sign-in failed", it)
                        onFailure(it)
                    }
                }
            }
    }
}