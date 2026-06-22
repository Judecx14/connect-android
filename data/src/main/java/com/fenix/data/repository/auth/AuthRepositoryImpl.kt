package com.fenix.data.repository.auth

import com.fenix.domain.model.auth.AuthState
import com.fenix.domain.model.auth.Credentials
import com.fenix.domain.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val firebaseAuthDataSource: FirebaseAuth
) : AuthRepository {
    override suspend fun signUp(credentials: Credentials): String? {
        return try {
            val result = firebaseAuthDataSource.createUserWithEmailAndPassword(
                credentials.email,
                credentials.password
            ).await()

            val user = result.user

            user?.uid
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun login(credentials: Credentials): Boolean {
        return try {
            val result = firebaseAuthDataSource.signInWithEmailAndPassword(
                credentials.email,
                credentials.password
            ).await()

            result.user != null
        } catch (e: Exception) {
            false
        }
    }

    override fun logout(): Boolean {
        return try {
            firebaseAuthDataSource.signOut()
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun currentAuthState(): AuthState {
        val currentUser = firebaseAuthDataSource.currentUser

        return if (currentUser != null) {
            AuthState.Authenticated
        } else {
            AuthState.Unauthenticated
        }
    }
}