package com.example.tomovanguard_tugas.ui.Presentation.Alarm.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun TimePickerDialog(
    modifier: Modifier = Modifier,
    title: String = "Select Time",
    onDissmissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dissmissButton: @Composable (() -> Unit)? = null,
    containerColor : Color = MaterialTheme.colorScheme.surface,
    content:@Composable () -> Unit
){
    Dialog(onDismissRequest = onDissmissRequest,
        properties =  DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape =MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ){
            Column(
                modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = title,
                    modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ){
                    Spacer(modifier = modifier.weight(1f))
                    dissmissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}