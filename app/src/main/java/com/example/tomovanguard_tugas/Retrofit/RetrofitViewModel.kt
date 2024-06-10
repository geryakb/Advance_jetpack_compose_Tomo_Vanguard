package com.example.tomovanguard_tugas.Retrofit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val remoteDataRepository: RemoteDataRepository
): ViewModel() {
    val response: MutableState<ApiState> = mutableStateOf(ApiState.Idle)

    fun getRandomQuote() = viewModelScope.launch {
        remoteDataRepository.getRandomQuote()
            .onStart {
                response.value = ApiState.Loading
            }.catch {
                response.value = ApiState.Failure(it)
            }.collect {
                response.value = ApiState.Success(it)
            }
    }
}