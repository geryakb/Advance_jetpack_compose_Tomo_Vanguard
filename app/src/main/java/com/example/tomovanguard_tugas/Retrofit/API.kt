package com.example.tomovanguard_tugas.Retrofit

import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET(RetrofitInstance.END_POINT)
    suspend fun getRandomQuote(): Response<Model>
}