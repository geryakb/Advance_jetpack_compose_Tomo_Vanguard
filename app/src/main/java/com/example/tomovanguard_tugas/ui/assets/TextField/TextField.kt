package com.example.tomovanguard_tugas.ui.assets.TextField

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun TomoTextField(
    modifier: Modifier = Modifier,
    value : String,
    onChangeValue : (String) -> Unit,
    label : String,
    placeholder : String,
    showContent : Boolean = false,
    height : Dp,
    width : Dp,
){

    OutlinedTextField(
        value = value,
        onValueChange = onChangeValue,
        placeholder = {
            Text(text = placeholder)
        },
        singleLine = true,
        visualTransformation = if (showContent){
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        label = {
            Text(text = label)
        },
        modifier = modifier
            .width(width)
            .height(height)
    )
}

