package com.example.tomovanguard_tugas.ui.Presentation.Room.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RoomEmptyTasktScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Tidak ada Kegiatan")
    }
}