package com.example.tomovanguard_tugas.Retrofit

import retrofit2.Response

sealed class ApiState {
    data class Success(val data: Response<Model>) : ApiState()
    data class Failure(val error: Throwable): ApiState()
    data object Loading : ApiState()
    data object Idle : ApiState()
}