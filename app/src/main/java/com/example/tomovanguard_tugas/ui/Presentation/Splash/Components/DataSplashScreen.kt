package com.example.tomovanguard_tugas.ui.Presentation.Splash.Components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.example.tomovanguard_tugas.DataStore.DataStore
import com.example.tomovanguard_tugas.Route.Screen
import kotlinx.coroutines.delay

@Composable
fun DataSplashScreen(
    modifier: Modifier = Modifier,
    navController : NavController
){
    val context = LocalContext.current
    val dataStore = DataStore(context)
    val statusLoggedIn = dataStore.getStatusLogin.collectAsState(initial = false)

    LaunchedEffect(
        key1 = true,
        block = {
            delay(2000L)
            if (statusLoggedIn.value) {
                navController.navigate(Screen.Room.route) {
                    popUpTo(Screen.DataSplash.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.DataSplash.route) {
                        inclusive = true
                    }
                }
            }
        }
    )
}