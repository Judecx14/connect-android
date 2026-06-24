package com.fenix.data.repository.auth

import com.fenix.data.datasource.api.safeCall
import com.fenix.domain.model.auth.AuthState
import com.fenix.domain.model.auth.AuthCredentials
import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import com.fenix.domain.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val firebaseAuthDataSource: FirebaseAuth
) : AuthRepository {
    override suspend fun signUp(credentials: AuthCredentials): Resource<Unit, FailureReason> {
        return safeCall(
            call = {
                firebaseAuthDataSource.createUserWithEmailAndPassword(
                    credentials.email,
                    credentials.password
                ).await()
            },
        )
    }

    override suspend fun login(credentials: AuthCredentials): Resource<Unit, FailureReason> {
        return safeCall(
            call = {
                firebaseAuthDataSource.signInWithEmailAndPassword(
                    credentials.email,
                    credentials.password
                ).await()
            },
        )
    }

    override suspend fun getJwt(): Resource<String, FailureReason> {
        return safeCall(
            call = {
                val result = firebaseAuthDataSource.getAccessToken(true).await()
                val token = requireNotNull(result.token)
                token
            },
            onSuccess = { token -> token }
        )
    }

    override fun logout(): Resource<Unit, FailureReason> {
        return safeCall(
            call = {
                firebaseAuthDataSource.signOut()
            }
        )
    }

    override fun getAuthProviderId(): Resource<String, FailureReason> {
        return safeCall(
            call = {
                val user = requireNotNull(firebaseAuthDataSource.currentUser)
                user.uid
            },
            onSuccess = { uid -> uid }
        )
    }

    override fun getAuthState(): Resource<AuthState, FailureReason> {
        return safeCall(
            call = {
                firebaseAuthDataSource.currentUser
            },
            onSuccess = { currentUser ->
                if (currentUser != null) AuthState.Authenticated else AuthState.Unauthenticated
            }
        )
    }
}