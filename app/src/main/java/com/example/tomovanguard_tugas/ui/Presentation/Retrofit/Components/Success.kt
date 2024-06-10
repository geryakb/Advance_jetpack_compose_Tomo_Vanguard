package com.example.tomovanguard_tugas.ui.Presentation.Retrofit.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FormatQuote
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tomovanguard_tugas.Retrofit.Model

@Composable
fun onSuccess(
    modifier: Modifier = Modifier,
    model: Model
){
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        model.tags.forEach {
            AssistChip(onClick = {

            }, label = {
                Text(text = it)
            })
        }
        Icon(imageVector = Icons.Rounded.FormatQuote,
            contentDescription = null,
            modifier
                .size(140.dp)
        )
        Text(text = model.content,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier.size(8.dp))
        Box(
            modifier = modifier
                .fillMaxWidth(.96f),
            contentAlignment = Alignment.CenterEnd
        ){
            Text(text = "- ${model.author}",
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}