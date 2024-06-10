package com.example.tomovanguard_tugas.ui.Presentation.Register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tomovanguard_tugas.R
import com.example.tomovanguard_tugas.Route.Screen
import com.example.tomovanguard_tugas.ui.Presentation.Login.LoginViewModel
import com.example.tomovanguard_tugas.ui.assets.Button.TomoButton
import com.example.tomovanguard_tugas.ui.assets.TextField.TomoTextField
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.icon_register))
    val state = viewModel.state.collectAsState(initial = null)
    val logoAnimationState =
        animateLottieCompositionAsState(composition = composition, iterations = Int.MAX_VALUE)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
        TomoButton(
            buttonTitle = "Daftar",
            onClicked = {
                coroutine.launch {
                    when {
                        email.isBlank() || password.isBlank() -> {
                            Toast.makeText(context, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                        }
                        password.length < 7 -> {
                            Toast.makeText(context, "Password minimal 7 huruf", Toast.LENGTH_SHORT).show()
                        }

                        else ->{
                            viewModel.registerUser(email, password){
                                Log.d("Berhasil", email)
                            }
                            email = ""
                            password = ""
                            Toast.makeText(context, "Akun Berhasil Dibuat", Toast.LENGTH_SHORT).show()
                            navController.navigate(Screen.Login.route){
                                popUpTo(Screen.Register.route){
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            },
            width = 150.dp,
            height = 50.dp)
    }
}