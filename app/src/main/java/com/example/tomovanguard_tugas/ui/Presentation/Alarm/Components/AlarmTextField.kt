package com.example.tomovanguard_tugas.ui.Presentation.Alarm.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AlarmTextField(
    modifier: Modifier = Modifier,
    value:String,
    onValueChange: (String) -> Unit,
    onClicked : () -> Unit,
    trailingIconResId: Int,
    placeholder : String,
    label : String
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            Icon(
                painter = painterResource(id = trailingIconResId),
                contentDescription = null,
                modifier.clickable {
                    onClicked()
                }
                    .size(20.dp)
            )
        },
        placeholder = {
            Text(text = placeholder)
        },
        label = {
            Text(text = label)
        },
        modifier = modifier
            .fillMaxWidth()
    )
}