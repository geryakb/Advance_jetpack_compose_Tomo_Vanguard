package com.example.tomovanguard_tugas.ui.Presentation.Login

import com.google.firebase.auth.AuthResult

data class LoginState(
    val success: String? = "",
    val error: String? = "",
    val loading: Boolean = false
)

data class LoginGoogleState(
    val success: AuthResult? = null,
    val error: String? = "",
    val loading: Boolean = true
)