package com.example.tomovanguard_tugas.Retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class RemoteDataRepository @Inject constructor(
    private val modelApi: API
) {
    fun getRandomQuote(): Flow<Response<Model>> = flow {
        emit(modelApi.getRandomQuote())
    }.flowOn(Dispatchers.IO)
}