package com.example.todoapp.model.iml

import com.example.todoapp.model.User
import com.example.todoapp.model.service.AccountService
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AccountServiceImpl @Inject constructor() : AccountService {

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listerner = FirebaseAuth.AuthStateListener { auth ->
                auth.currentUser?.let { User(it.uid) }?.let { this.trySend(it) }
            }

            Firebase.auth.addAuthStateListener(listerner)
            awaitClose { Firebase.auth.removeAuthStateListener(listerner) }
        }

    override val currentUserId: String
        get() = Firebase.auth.currentUser?.uid.orEmpty()

    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }
    
    override suspend fun signIn(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signUp(email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }


}