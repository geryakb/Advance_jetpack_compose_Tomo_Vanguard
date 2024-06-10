package com.example.tomovanguard_tugas.ui.Presentation.Login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tomovanguard_tugas.Firebase.AuthRepository
import com.example.tomovanguard_tugas.Firebase.Resource
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    private val _state = Channel<LoginState>()
    val state = _state.receiveAsFlow()

    private val _stateGoogle = mutableStateOf(LoginGoogleState())
    val stateGoogle: State<LoginGoogleState> = _stateGoogle

    fun loginUser(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.loginUser(email, password).collect {
                when (it) {
                    is Resource.Error -> {
                        _state.send(LoginState(error = it.message))
                        callback(false)
                    }
                    is Resource.Loading -> {
                        _state.send(LoginState(loading = true))
                    }
                    is Resource.Success -> {
                        _state.send(LoginState(success = "Login Berhasil"))
                        callback(true)
                    }
                }
            }
        }
    }

    fun registerUser(email: String, password: String, home: () -> Unit) {
        viewModelScope.launch {
            repository.registerUser(email, password).collect {
                when (it) {
                    is Resource.Error -> {
                        _state.send(LoginState(error = it.message))
                    }
                    is Resource.Loading -> {
                        _state.send(LoginState(loading = true))
                    }
                    is Resource.Success -> {
                        _state.send(LoginState(success = "Daftar Berhasil"))
                        home()
                    }
                }
            }
        }
    }

    fun loginWithGoogle(credential: AuthCredential, home: () -> Unit){
        viewModelScope.launch {
            repository.loginWithGoogle(credential).collect {result ->
                when (result){
                    is Resource.Error -> {
                        _stateGoogle.value = LoginGoogleState(error = result.message)
                    }
                    is Resource.Loading -> {
                        _stateGoogle.value = LoginGoogleState(loading = true)
                    }
                    is Resource.Success -> {
                        _stateGoogle.value = LoginGoogleState(success = result.data)
                    }
                }
            }
        }
    }
}
