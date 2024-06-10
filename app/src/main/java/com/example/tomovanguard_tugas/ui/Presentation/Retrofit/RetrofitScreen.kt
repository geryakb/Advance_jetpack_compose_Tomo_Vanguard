package com.example.tomovanguard_tugas.ui.Presentation.Retrofit

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tomovanguard_tugas.Retrofit.ApiState
import com.example.tomovanguard_tugas.Retrofit.RetrofitViewModel
import com.example.tomovanguard_tugas.ui.Presentation.Retrofit.Components.onError
import com.example.tomovanguard_tugas.ui.Presentation.Retrofit.Components.onLoading
import com.example.tomovanguard_tugas.ui.Presentation.Retrofit.Components.onSuccess
import kotlinx.coroutines.launch

@Composable
fun RetrofitScreen(
    modifier: Modifier = Modifier,
    viewModel: RetrofitViewModel
){
    val apiState = viewModel.response.value
    val scope = rememberCoroutineScope()

    LaunchedEffect(
        key1 = true,
        block = { viewModel.getRandomQuote() }
    )
    Scaffold(
        floatingActionButton =
        {
            FloatingActionButton(onClick = {
                scope.launch {
                    viewModel.getRandomQuote()
                }
            }) {
                Icon(imageVector = Icons.Rounded.Shuffle,
                    contentDescription = null,
                )
            }
        }
    ){paddingValues ->
        AnimatedContent(targetState = apiState,
            label = "animated_content",
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearEasing
                    )
                ) togetherWith fadeOut(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearEasing
                    )
                )
            }) {
            when (it){
                is ApiState.Failure -> {
                    onError(error = it.error.message?: "Something Went Wrong")
                }
                ApiState.Loading -> {
                    onLoading()
                }
                is ApiState.Success -> {
                    val model = it.data.body()!!
                    onSuccess(model = model)
                }
                else -> {
                    Unit
                }
            }
        }
    }
}