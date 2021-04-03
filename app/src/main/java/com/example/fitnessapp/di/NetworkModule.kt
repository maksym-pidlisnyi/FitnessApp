package com.example.fitnessapp.di

import com.example.fitnessapp.network.ExerciseService
import com.example.fitnessapp.util.Constants.Companion.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

//    for testing
//    @Provides
//    @Singleton
//    fun provideRetrofit(moshi: Moshi): Retrofit =
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ExerciseService =
        retrofit.create(ExerciseService::class.java)

}