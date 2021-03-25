package com.example.fitnessapp.network

import com.example.fitnessapp.network.data.ExerciseContainer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ExerciseService {

    @GET("exerciseinfo")
    suspend fun getExercises(@Query("language") language: String = "2"): Deferred<ExerciseContainer>
}


///**
// * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
// * full Kotlin compatibility.
// */
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
///**
// * Main entry point for network access. Call like `Network.devbytes.getPlaylist()`
// */
//object Network {
//    // Configure retrofit to parse JSON and use coroutines
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://wger.de/api/v2/")
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .build()
//
//    val exercises = retrofit.create(ExerciseService::class.java)
//}
