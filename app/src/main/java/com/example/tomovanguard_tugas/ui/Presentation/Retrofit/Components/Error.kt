package com.example.tomovanguard_tugas.ui.Presentation.Retrofit.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun onError(
    modifier: Modifier = Modifier,
    error : String
){
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
    ){
        Text(text = error,
            modifier.padding(16.dp)
        )
    }
}