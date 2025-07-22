package com.example.vawcsanpedro2.backendmodel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class EncryptionRequest(val text: String)
data class EncryptionResponse(val encrypted: String)

interface EncryptionApiService {
    @POST("encrypt")
    fun encryptText(@Body request: EncryptionRequest): Call<EncryptionResponse>
}
