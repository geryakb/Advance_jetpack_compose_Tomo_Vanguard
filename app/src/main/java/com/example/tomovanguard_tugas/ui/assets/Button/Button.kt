package com.example.tomovanguard_tugas.ui.assets.Button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TomoButton(
    modifier: Modifier = Modifier,
    buttonTitle : String,
    onClicked : () -> Unit,
    width : Dp,
    height : Dp
){
    OutlinedButton(onClick = {
        onClicked()
    },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .width(width)
            .height(height)
    ){
        Text(text = buttonTitle, color = MaterialTheme.colorScheme.primary)
    }
}