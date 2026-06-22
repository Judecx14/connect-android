package com.fenix.data.di.repository

import com.fenix.data.repository.auth.AuthRepositoryImpl
import com.fenix.data.repository.location.LocationRepositoryImpl
import com.fenix.data.repository.user.UserRepositoryImpl
import com.fenix.domain.repository.auth.AuthRepository
import com.fenix.domain.repository.location.LocationRepository
import com.fenix.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}