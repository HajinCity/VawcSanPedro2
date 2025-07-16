package com.example.vawcsanpedro2.backendmodel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EncryptionClient {
    private const val BASE_URL = "https://us-central1-vawcsanpedropagadian.cloudfunctions.net/" // Replace with your project ID

    val api: EncryptionApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EncryptionApiService::class.java)
    }
}
