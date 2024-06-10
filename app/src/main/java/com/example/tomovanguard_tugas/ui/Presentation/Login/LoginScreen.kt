package com.example.tomovanguard_tugas.ui.Presentation.Login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tomovanguard_tugas.DataStore.DataStore
import com.example.tomovanguard_tugas.R
import com.example.tomovanguard_tugas.Route.Screen
import com.example.tomovanguard_tugas.SharedPreferences.SharedPreferencesManager
import com.example.tomovanguard_tugas.ui.assets.Button.TomoButton
import com.example.tomovanguard_tugas.ui.assets.TextField.TomoTextField
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel : LoginViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_icon))
    val state = viewModel.state.collectAsState(initial = null)
    val logoAnimationState =
        animateLottieCompositionAsState(composition = composition, iterations = Int.MAX_VALUE)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val dataStore = DataStore(context)
    val coroutine = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp)
    ){
            LottieAnimation(
                composition = composition,
                progress = { logoAnimationState.progress },
                modifier = Modifier
                    .size(352.dp)
            )
        TomoTextField(
            value = email,
            onChangeValue = { email = it },
            label = "Masukkan Emailmu",
            placeholder = "Email",
            height = 61.dp,
            width = 354.dp
        )
        TomoTextField(
            value = password,
            onChangeValue = { password = it },
            label = "Masukkan password mu",
            placeholder = "Password",
            height = 61.dp,
            width = 354.dp,
            showContent = true
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ){
            TomoButton(
                buttonTitle = "Daftar",
                onClicked = {
                            navController.navigate(Screen.Register.route){
                                popUpTo(Screen.Login.route){
                                    inclusive = true
                                }
                            }
                },
                width = 150.dp,
                height = 50.dp
            )
            TomoButton(
                buttonTitle = "Login",
                onClicked = {
                    coroutine.launch {
                        if (email.isBlank() || password.isBlank()) {
                            Toast.makeText(
                                context,
                                "Nama Dan Password Wajib Diisi",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.loginUser(email, password) {
                                if (it) {
                                    sharedPreferencesManager.name = email
                                    sharedPreferencesManager.password = password
                                    coroutine.launch {
                                        dataStore.saveStatus(true)
                                    }
                                    navController.navigate(Screen.Room.route) {
                                        popUpTo(Screen.Login.route) {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Email atau pasword salah",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                },
                width = 150.dp,
                height = 50.dp
            )
        }
    }
}
