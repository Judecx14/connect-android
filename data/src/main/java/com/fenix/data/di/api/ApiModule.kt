package com.fenix.data.di.api

import com.fenix.data.datasource.api.user.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        val converterFactory = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        val contentType = "application/json; charset=utf-8".toMediaType()

        return converterFactory.asConverterFactory(contentType)
    }

    @Provides
    @Singleton
    fun provideRetrofit(converterFactory: Converter.Factory): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("http://192.168.0.7:3000/api/")
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}